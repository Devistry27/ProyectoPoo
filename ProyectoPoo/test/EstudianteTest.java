package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Estudiante;

public class EstudianteTest {

    @Test
    public void testCrearEstudiante() {
        Estudiante estudiante = new Estudiante("Ana", "ana@mail.com", "clave", "Estudiante", "Ingeniería");
        assertEquals("Ana", estudiante.getNombre());
        assertEquals("ana@mail.com", estudiante.getCorreo());
        assertEquals("Ingeniería", estudiante.getCarrera());
    }

    @Test
    public void testCalcularMulta() {
        Estudiante estudiante = new Estudiante("Luis", "luis@mail.com", "clave", "Estudiante", "Derecho");
        assertEquals(300, estudiante.calcularMulta(3), 0.01);
        assertEquals(0, estudiante.calcularMulta(0), 0.01);
    }

    @Test
    public void testObtenerLimitePrestamo() {
        Estudiante estudiante = new Estudiante("Pedro", "pedro@mail.com", "clave", "Estudiante", "Arquitectura");
        assertEquals(5, estudiante.obtenerLimitePrestamo());
    }
}
