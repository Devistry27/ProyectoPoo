package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.LibroEspecializado;

public class LibroEspecializadoTest {

    @Test
    public void testCrearLibroEspecializado() {
        LibroEspecializado libro = new LibroEspecializado("Algoritmos", "456", "Knuth", "Computación", "Estante 2", "Físico");
        assertEquals("Algoritmos", libro.getTitulo());
        assertEquals("456", libro.getIsbn());
        assertEquals("Knuth", libro.getAutor());
        assertEquals("Computación", libro.getCategoria());
        assertEquals("Estante 2", libro.getUbicacionFisica());
        assertEquals("Físico", libro.getFormato());
        assertTrue(libro.estaDisponible());
    }

    @Test
    public void testReservarYDevolverLibroEspecializado() {
        LibroEspecializado libro = new LibroEspecializado("Algoritmos", "456", "Knuth", "Computación", "Estante 2", "Físico");
        libro.reservar();
        assertFalse(libro.estaDisponible());
        libro.devolver();
        assertTrue(libro.estaDisponible());
    }

    @Test
    public void testBuscarPorTituloAutorISBN() {
        LibroEspecializado libro = new LibroEspecializado("Algoritmos", "456", "Knuth", "Computación", "Estante 2", "Físico");
        assertTrue(libro.buscar("Algoritmos"));
        assertTrue(libro.buscarPorAutor("Knuth"));
        assertTrue(libro.buscarPorISBN("456"));
        assertFalse(libro.buscar("Otro"));
        assertFalse(libro.buscarPorAutor("OtroAutor"));
        assertFalse(libro.buscarPorISBN("999"));
    }
}
