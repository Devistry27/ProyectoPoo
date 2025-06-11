package model;

public abstract class Usuario {

    protected String nombre;
    protected String contrasena;
    protected String correo;
    protected String rol;

    public Usuario(String nombre, String correo, String contrasena, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Autenticación
    public boolean verificarCredenciales(String correo, String contrasena) {
        return this.correo.equalsIgnoreCase(correo) && this.contrasena.equals(contrasena);
    }

    // Actualización de datos personales
    public void actualizarInformacion(String nuevoNombre, String nuevaContrasena) {
        this.nombre = nuevoNombre;
        this.contrasena = nuevaContrasena;
        System.out.println("Información actualizada con éxito.");
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Rol: " + rol);
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre + ", Correo: " + correo + ", Rol: " + rol);
    }
        // Métodos abstractos que deben implementar las subclases
    public double calcularMulta(int diasRetraso) {
    double tarifa = switch (this.getRol().toLowerCase()) {
        case "estudiante" -> 0.5;
        case "profesor" -> 0.3;
        case "bibliotecario" -> 0.1;
        default -> 1.0;
    };
    return diasRetraso * tarifa;
}

    public abstract int obtenerLimitePrestamo();
}