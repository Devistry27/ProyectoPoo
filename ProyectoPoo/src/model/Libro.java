// Paquete: model
package model;

public abstract class Libro extends Material {

    protected String isbn;
    protected String autor;
    protected String categoria;
    protected String ubicacionFisica;
    protected boolean disponible;
    protected String formato; // "Físico", "eBook", "Audiolibro"

    public Libro(String titulo, String isbn, String autor, String categoria, String ubicacionFisica, String formato) {
        super(titulo, isbn); // Si Material tiene este constructor
        this.isbn = (isbn != null) ? isbn : "Sin ISBN";
        this.autor = (autor != null) ? autor : "Sin autor";
        this.categoria = (categoria != null) ? categoria : "Sin categoría";
        this.ubicacionFisica = (ubicacionFisica != null) ? ubicacionFisica : "Sin ubicación";
        this.formato = (formato != null) ? formato : "Sin formato";
        this.disponible = true;
    }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
        this.codigo = isbn; // mantener consistencia
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getUbicacionFisica() { return ubicacionFisica; }
    public void setUbicacionFisica(String ubicacionFisica) { this.ubicacionFisica = ubicacionFisica; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    public boolean estaDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public void reservar() {
        if (disponible) {
            disponible = false;
            System.out.println("Libro reservado con éxito.");
        } else {
            System.out.println("El libro no está disponible para reserva.");
        }
    }

    public void devolver() {
        disponible = true;
        System.out.println("Libro devuelto con éxito.");
    }

    public boolean buscar(String titulo) {
        return this.titulo != null && this.titulo.equalsIgnoreCase(titulo);
    }

    public boolean buscarPorAutor(String autor) {
        return this.autor != null && this.autor.equalsIgnoreCase(autor);
    }

    public boolean buscarPorISBN(String isbn) {
        return this.isbn != null && this.isbn.equalsIgnoreCase(isbn);
    }

    public abstract void mostrarInfo();
}
