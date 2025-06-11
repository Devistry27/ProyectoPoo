package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Prestamo;
import model.Estudiante;
import model.LibroFisico;

import java.time.LocalDate;

public class PrestamoTest {

    @Test
    public void testCrearPrestamo() {
        Estudiante usuario = new Estudiante("Ana", "ana@mail.com", "clave", "Estudiante", "Ingeniería");
        LibroFisico libro = new LibroFisico("POO", "789", "Deitel", "Programación", "Estante 5", "Físico");
        Prestamo prestamo = new Prestamo(usuario, libro);

        assertEquals(usuario, prestamo.getUsuario());
        assertEquals(libro, prestamo.getLibro());
        assertEquals(LocalDate.now(), prestamo.getFechaPrestamo());
        assertEquals(LocalDate.now().plusDays(usuario.obtenerLimitePrestamo()), prestamo.getFechaDevolucion());
        assertFalse(prestamo.isDevuelto());
    }

    @Test
    public void testMarcarDevuelto() {
        Estudiante usuario = new Estudiante("Luis", "luis@mail.com", "clave", "Estudiante", "Derecho");
        LibroFisico libro = new LibroFisico("Algoritmos", "456", "Knuth", "Computación", "Estante 2", "Físico");
        Prestamo prestamo = new Prestamo(usuario, libro);

        prestamo.marcarDevuelto();
        assertTrue(prestamo.isDevuelto());
        assertEquals(LocalDate.now(), prestamo.getFechaRetorno());
        assertTrue(libro.estaDisponible());
    }

    @Test
    public void testRenovarPrestamo() {
        Estudiante usuario = new Estudiante("Pedro", "pedro@mail.com", "clave", "Estudiante", "Arquitectura");
        LibroFisico libro = new LibroFisico("Estructuras", "111", "Smith", "CS", "Estante 1", "Físico");
        Prestamo prestamo = new Prestamo(usuario, libro);

        LocalDate fechaOriginal = prestamo.getFechaDevolucion();
        prestamo.renovar();
        assertEquals(fechaOriginal.plusDays(5), prestamo.getFechaDevolucion());
    }

}