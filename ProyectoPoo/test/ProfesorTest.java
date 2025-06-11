package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Profesor;

public class ProfesorTest {

    @Test
    public void testCrearProfesor() {
        Profesor profesor = new Profesor("Carlos", "carlos@mail.com", "clave", "Profesor", "Matemáticas");
        assertEquals("Carlos", profesor.getNombre());
        assertEquals("carlos@mail.com", profesor.getCorreo());
        assertEquals("Matemáticas", profesor.getDepartamento());
    }

    @Test
    public void testCalcularMulta() {
        Profesor profesor = new Profesor("Ana", "ana@mail.com", "clave", "Profesor", "Física");
        assertEquals(250, profesor.calcularMulta(5), 0.01);
        assertEquals(0, profesor.calcularMulta(0), 0.01);
    }

    @Test
    public void testObtenerLimitePrestamo() {
        Profesor profesor = new Profesor("Luis", "luis@mail.com", "clave", "Profesor", "Química");
        assertEquals(10, profesor.obtenerLimitePrestamo());
    }
}
