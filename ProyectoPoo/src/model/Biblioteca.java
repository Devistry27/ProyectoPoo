// Paquete: model
package model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import DAO.LibroDao;
import DAO.PrestamoDao;
import DAO.MultaDao;

public class Biblioteca {

    private final LibroDao libroDAO;
    private final PrestamoDao prestamoDAO;
    private final MultaDao multaDAO;
    private final AuthService authService;

    public Biblioteca(AuthService authService) {
        this.authService = authService;
        this.libroDAO = new LibroDao();
        this.prestamoDAO = new PrestamoDao();
        this.multaDAO = new MultaDao();
    }

    public void agregarLibro(String titulo, String autor, String isbn, String categoria, String ubicacion,
            String formato) {
        if (libroDAO.findByIsbn(isbn) == null) {
            Libro libro;
            switch (formato.toLowerCase()) {
                case "ebook", "audiolibro" ->
                    libro = new LibroEspecializado(titulo, isbn, autor, categoria, ubicacion, formato);
                default -> libro = new LibroFisico(titulo, isbn, autor, categoria, ubicacion);
            }
            libroDAO.save(libro);
        }
    }

    public boolean prestarLibro(String isbn, Usuario usuario) {
        List<Multa> multas = multaDAO.findAll().stream()
                .filter(m -> m.getUsuario().equals(usuario) && !m.isPagada())
                .collect(Collectors.toList());

        if (!multas.isEmpty())
            return false; // Bloqueado por multas

        Libro libro = libroDAO.findByIsbn(isbn);
        if (libro != null && libro.estaDisponible()) {
            Prestamo prestamo = new Prestamo(usuario, libro);
            prestamoDAO.save(prestamo);
            libro.setDisponible(false);
            libroDAO.update(libro);
            return true;
        }
        return false;
    }

    public Prestamo devolverLibro(String isbn) {
        for (Prestamo p : prestamoDAO.findAll()) {
            if (p.getLibro().getIsbn().equalsIgnoreCase(isbn) && !p.isDevuelto()) {
                p.marcarDevuelto();
                libroDAO.update(p.getLibro());

                if (p.estaAtrasado()) {
                    int dias = p.diasDeRetraso();
                    Multa multa = new Multa(p.getUsuario(), p.getLibro(), dias);
                    multaDAO.save(multa);
                }

                prestamoDAO.update(p);
                return p;
            }
        }
        return null;
    }

    public boolean renovarPrestamo(String isbn, Usuario usuario) {
        for (Prestamo p : prestamoDAO.findAll()) {
            if (p.getUsuario().equals(usuario) && p.getLibro().getIsbn().equalsIgnoreCase(isbn)) {
                p.renovar();
                return true;
            }
        }
        return false;
    }

    public boolean pagarMulta(Multa multa) {
        if (multa == null || multa.isPagada()) {
            return false;
        }

        multa.setPagada(true);
        multaDAO.update(multa);
        return true;
    }

    public Usuario autenticarUsuario(String correo, String clave, String rol) {
        Usuario usuario = authService.login(correo, clave);
        if (usuario != null) {
            return switch (rol.toLowerCase()) {
                case "estudiante" -> (usuario instanceof Estudiante) ? usuario : null;
                case "profesor" -> (usuario instanceof Profesor) ? usuario : null;
                case "bibliotecario" -> (usuario instanceof Bibliotecario) ? usuario : null;
                default -> null;
            };
        }
        return null;
    }

    public void actualizarUsuario(Usuario usuario, String nuevoNombre, String nuevaClave) {
        usuario.actualizarInformacion(nuevoNombre, nuevaClave);
    }

    public double calcularMulta(Usuario usuario, int diasRetraso) {
        return usuario.calcularMulta(diasRetraso);
    }

    public Multa registrarMulta(Usuario usuario, Libro libro, int diasRetraso) {
        Multa multa = new Multa(usuario, libro, diasRetraso);
        multaDAO.save(multa);
        return multa;
    }

    /** Devuelve catálogo en formato String para mostrar en consola/GUI */
    public String obtenerCatalogo() {
        StringBuilder sb = new StringBuilder();
        for (Libro libro : libroDAO.findAll()) {
            sb.append(libro.getTitulo()).append(" | Autor: ").append(libro.getAutor())
                    .append(" | ISBN: ").append(libro.getIsbn())
                    .append(" | Categoría: ").append(libro.getCategoria())
                    .append(" | Ubicación: ").append(libro.getUbicacionFisica())
                    .append(" | Formato: ").append(libro.getFormato())
                    .append(" | Disponible: ").append(libro.estaDisponible() ? "Sí" : "No")
                    .append("\n");
        }
        return sb.toString();
    }

    /** Devuelve lista de libros como objetos */
    public List<Libro> obtenerCatalogoLibros() {
        return libroDAO.findAll();
    }

    public List<Prestamo> historialPrestamos(Usuario usuario) {
        return prestamoDAO.findAll().stream()
                .filter(p -> p.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    public List<Multa> multasUsuario(Usuario usuario) {
        return multaDAO.findAll().stream()
                .filter(m -> m.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    public void registrarUsuario(Usuario usuario) {
        authService.registrarUsuario(usuario);
    }
}
