package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private static int contador = 1;

    private final int id;
    private final Usuario usuario;
    private final Libro libro;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private LocalDate fechaRetorno;
    private boolean devuelto;

    public Prestamo(Usuario usuario, Libro libro) {
        this.id = contador++;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = fechaPrestamo.plusDays(usuario.obtenerLimitePrestamo());
        this.devuelto = false;
    }

    public int getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Libro getLibro() { return libro; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public LocalDate getFechaRetorno() { return fechaRetorno; }

    // ✅ MÉTODO FALTANTE
    public boolean isDevuelto() {
        return devuelto;
    }

    // ✅ MÉTODO FALTANTE
    public void marcarDevuelto() {
        this.fechaRetorno = LocalDate.now();
        this.devuelto = true;
        libro.setDisponible(true);
    }

    public boolean estaAtrasado() {
        return !devuelto && LocalDate.now().isAfter(fechaDevolucion);
    }

    public int diasDeRetraso() {
        if (!estaAtrasado()) return 0;
        return (int) ChronoUnit.DAYS.between(fechaDevolucion, LocalDate.now());  // ✅ fix long → int
    }

    public void renovar() {
        this.fechaDevolucion = fechaDevolucion.plusDays(5);
    }

    @Override
    public String toString() {
        return "Libro: " + libro.getTitulo() +
               " | Prestado: " + fechaPrestamo +
               " | Devuelve: " + fechaDevolucion +
               (fechaRetorno != null ? " | Retornado: " + fechaRetorno : "") +
               (estaAtrasado() ? " | Atrasado: " + diasDeRetraso() + " días" : "");
    }
}
