package model;

import java.util.UUID;

public class Multa {
    private final String id;             
    private final Usuario usuario;
    private final Libro libro;
    private final int diasRetraso;
    private final double monto;
    private boolean pagada;

    public Multa(Usuario usuario, Libro libro, int diasRetraso, double monto) {
        this.id = UUID.randomUUID().toString().substring(0, 8); 
        this.usuario = usuario;
        this.libro = libro;
        this.diasRetraso = diasRetraso;
        this.monto = usuario.calcularMulta(diasRetraso);
        this.pagada = false;
    }

    public String getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public double getMonto() {
        return monto;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void pagar() {
        this.pagada = true;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Libro: " + libro.getTitulo() +
               " | Días atraso: " + diasRetraso +
               " | Monto: $" + monto +
               " | Estado: " + (pagada ? "Pagada" : "Pendiente");
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
}
