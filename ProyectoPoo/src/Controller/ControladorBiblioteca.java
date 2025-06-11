package Controller;

import model.*;
import View.VistaPrincipal;
import DAO.MultaDao;
import DAO.PrestamoDao;
import Servicios.GestorMultas;

import java.util.List;

public class ControladorBiblioteca {

    private final VistaPrincipal vista;
    private final Biblioteca biblioteca;
    private final MultaDao multaDao = new MultaDao();
    private final PrestamoDao prestamoDao = new PrestamoDao();
    private Usuario usuarioActual;
    private final GestorMultas gestorMultas = new GestorMultas(multaDao);

    public ControladorBiblioteca(VistaPrincipal vista) {
        this.vista = vista;
        this.biblioteca = new Biblioteca(new AuthService());

        configurarEventos();
        vista.actualizarListaLibros(biblioteca.obtenerCatalogo());
    }

    private void configurarEventos() {
        vista.getBtnRegistrarUsuario().addActionListener(e -> registrarUsuario());
        vista.getBtnIniciarSesion().addActionListener(e -> iniciarSesion());
        vista.getBtnAgregarLibro().addActionListener(e -> agregarLibro());
        vista.getBtnPrestarLibro().addActionListener(e -> prestarLibro());
        vista.getBtnDevolverLibro().addActionListener(e -> devolverLibro());
        vista.getBtnActualizarUsuario().addActionListener(e -> actualizarUsuario());
        vista.getBtnCalcularMulta().addActionListener(e -> calcularMulta());
        vista.getBtnRenovarPrestamo().addActionListener(e -> renovarPrestamo());
        vista.getBtnVerHistorial().addActionListener(e -> mostrarHistorial());
        vista.getBtnPagarMulta().addActionListener(e -> pagarMulta());
        vista.getBtnVerMultas().addActionListener(e -> mostrarMultas());
    }

    private void registrarUsuario() {
        String nombre = vista.getNombreUsuario();
        String correo = vista.getCorreoUsuario();
        String clave = vista.getClaveUsuario();
        String rol = vista.getRolUsuario();
        String extra = vista.getCarreraDepartamento();

        Usuario nuevoUsuario;
        switch (rol) {
            case "Estudiante" -> nuevoUsuario = new Estudiante(nombre, correo, clave, rol, extra);
            case "Profesor" -> nuevoUsuario = new Profesor(nombre, correo, clave, rol, extra);
            case "Bibliotecario" -> nuevoUsuario = new Bibliotecario(nombre, correo, clave, rol);
            default -> {
                vista.mostrarMensaje("Rol inválido.");
                return;
            }
        }

        biblioteca.registrarUsuario(nuevoUsuario);
        vista.mostrarMensaje("Usuario registrado correctamente.");
    }

    private void iniciarSesion() {
        String correo = vista.getCorreoUsuario();
        String clave = vista.getClaveUsuario();
        String rol = vista.getRolUsuario();

        usuarioActual = biblioteca.autenticarUsuario(correo, clave, rol);
        if (usuarioActual != null) {
            vista.mostrarMensaje("Sesión iniciada como " + usuarioActual.getNombre());
        } else {
            vista.mostrarMensaje("Credenciales incorrectas o rol no coincide.");
        }
    }

    private void agregarLibro() {
        if (usuarioActual != null && usuarioActual.getRol().equalsIgnoreCase("Bibliotecario")) {
            String titulo = vista.getTituloLibro();
            String autor = vista.getAutorLibro();
            String isbn = vista.getISBNLibro();
            String categoria = vista.getCategoriaLibro();
            String ubicacion = vista.getUbicacionFisicaLibro();
            String formato = vista.getFormatoLibro();

            biblioteca.agregarLibro(titulo, autor, isbn, categoria, ubicacion, formato);
            vista.mostrarMensaje("Libro agregado correctamente.");
            vista.actualizarListaLibros(biblioteca.obtenerCatalogo());
        } else {
            vista.mostrarMensaje("No tiene permisos para agregar libros.");
        }
    }

    private void prestarLibro() {
        if (usuarioActual != null) {
            // Bloqueo por multas pendientes
            List<Multa> multasPendientes = multaDao.findByUsuario(usuarioActual);
            boolean tienePendientes = multasPendientes.stream().anyMatch(m -> !m.isPagada());

            if (tienePendientes) {
                vista.mostrarMensaje("No puede realizar préstamos hasta pagar sus multas pendientes.");
                return;
            }

            String isbn = vista.getISBNLibro();
            if (biblioteca.prestarLibro(isbn, usuarioActual)) {
                vista.mostrarMensaje("Libro prestado correctamente.");
            } else {
                vista.mostrarMensaje("No se puede prestar el libro. Verifique disponibilidad.");
            }
            vista.actualizarListaLibros(biblioteca.obtenerCatalogo());
        } else {
            vista.mostrarMensaje("Debe iniciar sesión primero.");
        }
    }

    private void devolverLibro() {
        String isbn = vista.getISBNLibro();
        Prestamo prestamo = biblioteca.devolverLibro(isbn);

        if (prestamo != null) {
            vista.mostrarMensaje("Libro devuelto exitosamente.");

            int diasRetraso = vista.getDiasRetraso();

            if (diasRetraso > 0) {
                gestorMultas.registrarMultaManual(prestamo, diasRetraso);

                // Mostrar la multa recién creada
                List<Multa> multas = gestorMultas.obtenerMultas(usuarioActual);
                Multa multa = multas.stream()
                        .filter(m -> m.getLibro().equals(prestamo.getLibro()) && m.getDiasRetraso() == diasRetraso
                                && !m.isPagada())
                        .findFirst().orElse(null);

                if (multa != null) {
                    vista.mostrarMensaje("Se generó una multa:\n" + multa);
                }
            } else {
                vista.mostrarMensaje("No hubo retraso. No se generó multa.");
            }

            vista.actualizarListaLibros(biblioteca.obtenerCatalogo());
        } else {
            vista.mostrarMensaje("No se encontró préstamo asociado a ese ISBN.");
        }
    }

    private void actualizarUsuario() {
        if (usuarioActual != null) {
            String nuevoNombre = vista.getNombreUsuario();
            String nuevaClave = vista.getClaveUsuario();
            biblioteca.actualizarUsuario(usuarioActual, nuevoNombre, nuevaClave);
            vista.mostrarMensaje("Información actualizada correctamente.");
        } else {
            vista.mostrarMensaje("Debe iniciar sesión primero.");
        }
    }

    private void calcularMulta() {
        if (usuarioActual != null) {
            int dias = vista.getDiasRetraso();
            double multa = biblioteca.calcularMulta(usuarioActual, dias);
            vista.mostrarMensaje("Multa calculada: $" + multa);
        } else {
            vista.mostrarMensaje("Debe iniciar sesión primero.");
        }
    }

    private void renovarPrestamo() {
        if (usuarioActual != null) {
            String isbn = vista.getISBNLibro();
            if (biblioteca.renovarPrestamo(isbn, usuarioActual)) {
                vista.mostrarMensaje("Préstamo renovado exitosamente.");
            } else {
                vista.mostrarMensaje("No se pudo renovar el préstamo.");
            }
        } else {
            vista.mostrarMensaje("Debe iniciar sesión primero.");
        }
    }

    private void mostrarHistorial() {
        if (usuarioActual != null) {
            List<Prestamo> historial = biblioteca.historialPrestamos(usuarioActual);
            if (historial.isEmpty()) {
                vista.mostrarMensaje("No tiene préstamos registrados.");
            } else {
                vista.mostrarMensaje("Historial de préstamos:");
                for (Prestamo p : historial) {
                    vista.mostrarMensaje(p.toString());
                }
            }
        } else {
            vista.mostrarMensaje("Debe iniciar sesión primero.");
        }
    }

    private void mostrarMultas() {
        if (usuarioActual == null) {
            vista.mostrarMensaje("Debe iniciar sesión primero.");
            return;
        }
        List<Multa> multas = gestorMultas.obtenerMultas(usuarioActual);

        if (multas.isEmpty()) {
            vista.mostrarMensaje("No tiene multas registradas.");
        } else {
            StringBuilder sb = new StringBuilder("Sus Multas:\n");
            for (Multa multa : multas) {
                sb.append(multa).append("\n");
            }
            vista.mostrarMensaje(sb.toString());
        }
    }

    private void pagarMulta() {
        if (usuarioActual == null) {
            vista.mostrarMensaje("Debe iniciar sesión primero.");
            return;
        }

        List<Multa> multas = multaDao.findByUsuario(usuarioActual);
        for (Multa multa : multas) {
            if (!multa.isPagada()) {
                multa.setPagada(true);
                vista.mostrarMensaje("Multa pagada:\n" + multa);
                return;
            }
        }

        vista.mostrarMensaje("No tiene multas pendientes por pagar.");
    }
}
