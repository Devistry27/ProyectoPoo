package model;

public class LibroFisico extends Libro {
    public LibroFisico(String titulo, String isbn, String autor, String categoria, String ubicacionFisica, String formato) {
        super(titulo, isbn, autor, categoria, ubicacionFisica, formato);
    }

    @Override
    public void mostrarInfo() {
        // Implementación vacía o personalizada
    }
}
