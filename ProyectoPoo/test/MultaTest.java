package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Multa;
import model.Estudiante;
import model.LibroFisico;

public class MultaTest {

    @Test
    public void testCrearMulta() {
        Estudiante usuario = new Estudiante("Ana", "ana@mail.com", "clave", "Estudiante", "Ingeniería");
        LibroFisico libro = new LibroFisico("POO", "789", "Deitel", "Programación", "Estante 5", "Físico");
        Multa multa = new Multa(usuario, libro, 3, 0); // El monto real lo calcula el constructor

        assertNotNull(multa.getId());
        assertEquals(usuario, multa.getUsuario());
        assertEquals(libro, multa.getLibro());
        assertEquals(3, multa.getDiasRetraso());
        assertEquals(300.0, multa.getMonto(), 0.01); // 3 * 100 según Estudiante
        assertFalse(multa.isPagada());
    }

    @Test
    public void testPagarMulta() {
        Estudiante usuario = new Estudiante("Luis", "luis@mail.com", "clave", "Estudiante", "Derecho");
        LibroFisico libro = new LibroFisico("Algoritmos", "456", "Knuth", "Computación", "Estante 2", "Físico");
        Multa multa = new Multa(usuario, libro, 2, 0);

        assertFalse(multa.isPagada());
        multa.pagar();
        assertTrue(multa.isPagada());
    }

    @Test
    public void testToString() {
        Estudiante usuario = new Estudiante("Pedro", "pedro@mail.com", "clave", "Estudiante", "Arquitectura");
        LibroFisico libro = new LibroFisico("Estructuras", "111", "Smith", "CS", "Estante 1", "Físico");
        Multa multa = new Multa(usuario, libro, 1, 0);
        String texto = multa.toString();
        assertTrue(texto.contains("ID: "));
        assertTrue(texto.contains("Libro: Estructuras"));
        assertTrue(texto.contains("Días atraso: 1"));
        assertTrue(texto.contains("Monto: $100.0"));
        assertTrue(texto.contains("Pendiente"));
        multa.pagar();
        assertTrue(multa.toString().contains("Pagada"));
    }
}
