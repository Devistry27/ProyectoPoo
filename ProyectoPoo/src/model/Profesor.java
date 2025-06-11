package model;

public class Profesor extends Usuario {

    private String departamento;

    public Profesor(String nombre, String correo, String contrasena,String rol, String departamento) {
        super(nombre, correo, contrasena, rol);
        this.departamento = departamento;
    }

    public double calcularMulta(int diasRetraso) {
        return diasRetraso * 50;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int obtenerLimitePrestamo() {
        return 10;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Departamento: " + departamento);
    }

    public void mostraropciones() {  //Una solucion es implementar las opciones de cada uno en sus clases y llamarlos en el main

    }
}
