package model;

public class Bibliotecario extends Usuario {

    public Bibliotecario(String nombre, String correo, String contrasena, String rol ) {
        super(nombre, correo, contrasena, rol);
    }

    public double calcularMulta(int diasRetraso) {
        return 0; // No aplica
    }

    public int obtenerLimitePrestamo() {
        return 0; // No aplica
    }

    // Funciones propias de bibliotecario
    public void registrarPrestamo(Usuario usuario, Libro libro) {
        // lógica para registrar préstamo
    }

}
