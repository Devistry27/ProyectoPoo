package model;

public class LibroEspecializado extends Libro {

    public LibroEspecializado(String titulo, String isbn, String autor, String categoria, String ubicacionFisica, String formato) {
        super(titulo, isbn, autor, categoria, ubicacionFisica, formato);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Libro Especializado: " + titulo +
                " | Autor: " + autor +
                " | ISBN: " + isbn +
                " | Categoría: " + categoria +
                " | Ubicación: " + ubicacionFisica +
                " | Formato: " + formato);
    }
} 
