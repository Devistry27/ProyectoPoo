package Servicios;

import java.util.ArrayList;
import java.util.List;
import model.Libro;

public class CatalogoLibros {

    private List<Libro> libros;

    public CatalogoLibros() {
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Libro buscarPorISBN(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo() != null && libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public void mostrarDisponibilidad(String isbn) {
        Libro libro = buscarPorISBN(isbn);
        if (libro != null) {
            System.out.println(libro.estaDisponible() ? "Disponible" : "No disponible");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
