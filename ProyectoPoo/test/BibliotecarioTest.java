package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;

import model.Biblioteca;
import model.Estudiante;
import model.Bibliotecario;
import model.Usuario;
import model.Libro;
import model.AuthService;
import model.Prestamo;

public class BibliotecarioTest {

    @Test
    public void testRegistrarPrestamoComoBibliotecario() {
        Biblioteca biblioteca = new Biblioteca(new AuthService());
        biblioteca.agregarLibro("Titulo", "Autor", "123", "Categoria", "Ubicacion", "fisico");
        Usuario bibliotecario = new Bibliotecario("Carlos", "Carlos@correo", "clave123", "Bibliotecario");
        Usuario estudiante = new Estudiante("Ana", "Ana@correo", "clave123", "Estudiante", "Derecho");

        // El bibliotecario presta el libro al estudiante
        boolean prestado = biblioteca.prestarLibro("123", estudiante);
        assertTrue(prestado);

        // Verifica que el libro ya no está disponible
        Libro libro = biblioteca.obtenerCatalogoLibros().get(0);
        assertFalse(libro.estaDisponible());
    }

    @Test
    public void testBibliotecarioPuedeAgregarLibro() {
        Biblioteca biblioteca = new Biblioteca(new AuthService());
        Usuario bibliotecario = new Bibliotecario("Carlos", "Carlos@correo", "clave123", "Bibliotecario");
        biblioteca.agregarLibro("Nuevo Libro", "Autor", "456", "Categoria", "Ubicacion", "fisico");
        Libro libro = biblioteca.obtenerCatalogoLibros().stream()
            .filter(l -> l.getIsbn().equals("456"))
            .findFirst().orElse(null);
        assertNotNull(libro);
        assertEquals("Nuevo Libro", libro.getTitulo());
    }
}