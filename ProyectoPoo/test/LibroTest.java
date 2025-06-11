package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.LibroFisico;

public class LibroTest {

    @Test
    public void testCrearLibro() {
        LibroFisico libro = new LibroFisico("Titulo", "123", "Autor", "Categoria", "Ubicacion", "Físico");
        assertEquals("Titulo", libro.getTitulo());
        assertEquals("123", libro.getIsbn());
        assertEquals("Autor", libro.getAutor());
        assertEquals("Categoria", libro.getCategoria());
        assertEquals("Ubicacion", libro.getUbicacionFisica());
        assertEquals("Físico", libro.getFormato());
        assertTrue(libro.estaDisponible());
    }

    @Test
    public void testReservarYDevolverLibro() {
        LibroFisico libro = new LibroFisico("Titulo", "123", "Autor", "Categoria", "Ubicacion", "Físico");
        libro.reservar();
        assertFalse(libro.estaDisponible());
        libro.devolver();
        assertTrue(libro.estaDisponible());
    }

    @Test
    public void testBuscarPorTituloAutorISBN() {
        LibroFisico libro = new LibroFisico("Titulo", "123", "Autor", "Categoria", "Ubicacion", "Físico");
        assertTrue(libro.buscar("Titulo"));
        assertTrue(libro.buscarPorAutor("Autor"));
        assertTrue(libro.buscarPorISBN("123"));
        assertFalse(libro.buscar("Otro"));
        assertFalse(libro.buscarPorAutor("OtroAutor"));
        assertFalse(libro.buscarPorISBN("999"));
    }
}