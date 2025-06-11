package model;

public class LibroFisico extends Libro {

    public LibroFisico(String titulo, String isbn, String autor, String categoria, String ubicacionFisica) {
        super(titulo, isbn, autor, categoria, ubicacionFisica, "Físico");
    }

    public void prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("Libro fisico prestado con exito.");
        } else {
            System.out.println("El libro no esta disponible para prestar.");
        }
    }

    public void renovar() {
        if (!disponible) {
            System.out.println("Prestamo renovado por una semana.");
        } else {
            System.out.println("El libro no esta prestado, no se puede renovar.");
        }
    }

  @Override
    public void mostrarInfo() {
        System.out.println("Libro Físico: " + titulo +
                " | Autor: " + autor +
                " | ISBN: " + isbn +
                " | Categoría: " + categoria +
                " | Ubicación: " + ubicacionFisica +
                " | Formato: " + formato +
                " | Disponible: " + (disponible ? "Sí" : "No"));
    }
}
