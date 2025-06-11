package model;

public class Estudiante extends Usuario {

    private String carrera;

    public Estudiante(String nombre, String correo, String contrasena,String rol, String carrera) {
        super(nombre, correo, contrasena, rol);
        this.carrera = carrera;
    }

    @Override
    public double calcularMulta(int diasRetraso) {
        return diasRetraso * 100;
    }

    @Override
    public int obtenerLimitePrestamo() {
        return 5;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Carrera: " + carrera);
    }
}
