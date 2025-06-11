package Servicios;

import model.Usuario;
import java.util.*;
import model.Libro;
import model.Prestamo;
import model.Usuario;

public class GestorPrestamos {

    private List<Prestamo> prestamos;
    private Map<Usuario, List<Prestamo>> historialUsuarios;

    public GestorPrestamos() {
        prestamos = new ArrayList<>();
        historialUsuarios = new HashMap<>();
    }

    public void registrarPrestamo(Usuario usuario, Libro libro) {
        if (libro.estaDisponible()) {
            Prestamo prestamo = new Prestamo(usuario, libro);
            prestamos.add(prestamo);
            historialUsuarios.computeIfAbsent(usuario, k -> new ArrayList<>()).add(prestamo);
            libro.setDisponible(false);
            System.out.println("Prestamo registrado con exito.");
        } else {
            System.out.println("El libro no esta disponible.");
        }
    }

    public void renovarPrestamo(Prestamo prestamo) {
        prestamo.renovar();
        System.out.println("Prestamo renovado hasta: " + prestamo.getFechaDevolucion());
    }

    public void mostrarHistorial(Usuario usuario) {
        List<Prestamo> historial = historialUsuarios.get(usuario);
        if (historial != null) {
            for (Prestamo p : historial) {
                System.out.println("Libro: " + p.getLibro().getTitulo()
                        + " | Desde: " + p.getFechaPrestamo()
                        + " | Hasta: " + p.getFechaDevolucion());
            }
        } else {
            System.out.println("El usuario no tiene historial.");
        }
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }   
}
