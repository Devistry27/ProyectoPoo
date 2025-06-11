package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;

import model.Biblioteca;
import model.Estudiante;
import model.AuthService;
import model.Libro;
import model.Usuario;
import model.Prestamo;
import model.Multa;

import java.util.List;

public class BibliotecaTest {

    @Test
    public void testAgregarLibro() {
        Biblioteca biblioteca = new Biblioteca(new AuthService());

        // Agregar un libro
        biblioteca.agregarLibro("Titulo", "Autor", "123", "Categoria", "Ubicacion", "fisico");

        // Verificar que el libro fue agregado
        List<Libro> catalogo = biblioteca.obtenerCatalogoLibros();
        assertEquals(1, catalogo.size());
        assertEquals("Titulo", catalogo.get(0).getTitulo());
        assertEquals("123", catalogo.get(0).getIsbn());
    }

    @Test
    public void testPrestarLibro() {
        Biblioteca biblioteca = new Biblioteca(new AuthService());
        biblioteca.agregarLibro("Titulo", "Autor", "123", "Categoria", "Ubicacion", "fisico");
        Usuario usuario = new Estudiante("Juan", "juan@mail.com", "clave", "Estudiante", "Ingeniería");
        boolean prestado = biblioteca.prestarLibro("123", usuario);
        assertTrue(prestado);
        List<Libro> catalogo = biblioteca.obtenerCatalogoLibros();
        assertFalse(catalogo.get(0).estaDisponible());
    }

    @Test
    public void testDevolverLibro() {
        Biblioteca biblioteca = new Biblioteca(new AuthService());
        biblioteca.agregarLibro("Titulo", "Autor", "123", "Categoria", "Ubicacion", "fisico");
        Usuario usuario = new Estudiante("Ana", "ana@mail.com", "clave", "Estudiante", "Derecho");
        biblioteca.prestarLibro("123", usuario);
        Prestamo prestamo = biblioteca.devolverLibro("123");
        assertNotNull(prestamo);
        List<Libro> catalogo = biblioteca.obtenerCatalogoLibros();
        assertTrue(catalogo.get(0).estaDisponible());
    }

    @Test
    public void testRenovarPrestamo() {
        Biblioteca biblioteca = new Biblioteca(new AuthService());
        biblioteca.agregarLibro("Titulo", "Autor", "123", "Categoria", "Ubicacion", "fisico");
        Usuario usuario = new Estudiante("Luis", "luis@mail.com", "clave", "Estudiante", "Medicina");
        biblioteca.prestarLibro("123", usuario);
        boolean renovado = biblioteca.renovarPrestamo("123", usuario);
        assertTrue(renovado);
    }

    @Test
    public void testRegistrarMulta() {
        Biblioteca biblioteca = new Biblioteca(new AuthService());
        biblioteca.agregarLibro("Titulo", "Autor", "123", "Categoria", "Ubicacion", "fisico");
        Usuario usuario = new Estudiante("Pedro", "pedro@mail.com", "clave", "Estudiante", "Arquitectura");
        Libro libro = biblioteca.obtenerCatalogoLibros().get(0);
        Multa multa = biblioteca.registrarMulta(usuario, libro, 3, 30.0);
        assertNotNull(multa);
        assertEquals(usuario, multa.getUsuario());
        assertEquals(libro, multa.getLibro());
        assertEquals(3, multa.getDiasRetraso());
        assertEquals(300.0, multa.getMonto(), 0.01);
    }

    @Test
    public void testCalcularMulta() {
        Biblioteca biblioteca = new Biblioteca(new AuthService());
        Usuario usuario = new Estudiante("Maria", "maria@mail.com", "clave", "Estudiante", "Psicología");
        double monto = biblioteca.calcularMulta(usuario, 5);
        assertTrue(monto >= 0); // Depende de la implementación de calcularMulta en Usuario
    }
}
