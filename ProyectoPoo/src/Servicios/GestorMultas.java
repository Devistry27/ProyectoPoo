package Servicios;

import model.Usuario;
import model.Prestamo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorMultas {

    private Map<Usuario, List<String>> multasPorUsuario = new HashMap<>();

    public void verificarMulta(Prestamo prestamo) {
        LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
        LocalDate hoy = LocalDate.now();
        Usuario usuario = prestamo.getUsuario();

        if (fechaDevolucion != null && hoy.isAfter(fechaDevolucion)) {
            int diasRetraso = (int) (hoy.toEpochDay() - fechaDevolucion.toEpochDay());
            double multa = usuario.calcularMulta(diasRetraso);
            registrarMulta(usuario, "Retraso de " + diasRetraso + " días. Multa: $" + multa);
        }
    }

    public void registrarMulta(Usuario usuario, String detalle) {
        multasPorUsuario.computeIfAbsent(usuario, k -> new ArrayList<>()).add(detalle);
    }

    public List<String> obtenerMultas(Usuario usuario) {
        return multasPorUsuario.getOrDefault(usuario, new ArrayList<>());
    }

    public void mostrarMultas(Usuario usuario) {
        List<String> multas = obtenerMultas(usuario);
        if (multas.isEmpty()) {
            System.out.println("No hay multas registradas para el usuario.");
        } else {
            System.out.println("Multas para " + usuario.getNombre() + ":");
            for (String multa : multas) {
                System.out.println("- " + multa);
            }
        }
    }
}
