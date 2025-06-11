package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.LibroFisico;

public class LibroFisicoTest {

    @Test
    public void testCrearLibroFisico() {
        LibroFisico libro = new LibroFisico("POO", "789", "Deitel", "Programación", "Estante 5", "Físico");
        assertEquals("POO", libro.getTitulo());
        assertEquals("789", libro.getIsbn());
        assertEquals("Deitel", libro.getAutor());
        assertEquals("Programación", libro.getCategoria());
        assertEquals("Estante 5", libro.getUbicacionFisica());
        assertEquals("Físico", libro.getFormato());
        assertTrue(libro.estaDisponible());
    }

    @Test
    public void testReservarYDevolverLibroFisico() {
        LibroFisico libro = new LibroFisico("POO", "789", "Deitel", "Programación", "Estante 5", "Físico");
        libro.reservar();
        assertFalse(libro.estaDisponible());
        libro.devolver();
        assertTrue(libro.estaDisponible());
    }

    @Test
    public void testBuscarPorTituloAutorISBN() {
        LibroFisico libro = new LibroFisico("POO", "789", "Deitel", "Programación", "Estante 5", "Físico");
        assertTrue(libro.buscar("POO"));
        assertTrue(libro.buscarPorAutor("Deitel"));
        assertTrue(libro.buscarPorISBN("789"));
        assertFalse(libro.buscar("Otro"));
        assertFalse(libro.buscarPorAutor("OtroAutor"));
        assertFalse(libro.buscarPorISBN("999"));
    }
}