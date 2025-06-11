package Servicios;

import DAO.MultaDao;
import model.Multa;
import model.Prestamo;
import model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class GestorMultas {

    private final MultaDao multaDao;

    public GestorMultas(MultaDao multaDao) {
        this.multaDao = multaDao;
    }

    public void verificar(Prestamo prestamo) {
        LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
        LocalDate hoy = LocalDate.now();
        Usuario usuario = prestamo.getUsuario();

        if (fechaDevolucion != null && hoy.isAfter(fechaDevolucion)) {
            int diasRetraso = (int) (hoy.toEpochDay() - fechaDevolucion.toEpochDay());
            double monto = usuario.calcularMulta(diasRetraso);
            Multa multa = new Multa(usuario, prestamo.getLibro(), diasRetraso, monto);
            multaDao.save(multa);
        }
    }

    public List<Multa> obtenerMultas(Usuario usuario) {
        return multaDao.findByUsuario(usuario);
    }

    public void mostrarMultasConsola(Usuario usuario) {
        List<Multa> multas = obtenerMultas(usuario);
        if (multas.isEmpty()) {
            System.out.println("No hay multas registradas para el usuario.");
        } else {
            System.out.println("Multas para " + usuario.getNombre() + ":");
            for (Multa multa : multas) {
                System.out.println("- " + multa);
            }
        }
    }

    public boolean pagarMulta(String idMulta) {
        Multa multa = multaDao.findAll().stream()
                .filter(m -> m.getId().equals(idMulta))
                .findFirst()
                .orElse(null);

        if (multa != null && !multa.isPagada()) {
            multa.setPagada(true);
            multaDao.update(multa);
            return true;
        }
        return false;
    }

    public void registrarMultaManual(Prestamo prestamo, int diasRetraso) {
        if (diasRetraso > 0) {
            Usuario usuario = prestamo.getUsuario();
            double monto = usuario.calcularMulta(diasRetraso);
            Multa multa = new Multa(usuario, prestamo.getLibro(), diasRetraso, monto);
            multaDao.save(multa);
        }
    }
}