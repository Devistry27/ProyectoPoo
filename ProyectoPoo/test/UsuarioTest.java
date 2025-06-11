package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Estudiante;

public class UsuarioTest {

    @Test
    public void testGettersSetters() {
        Estudiante usuario = new Estudiante("Ana", "ana@mail.com", "clave", "Estudiante", "Ingeniería");
        assertEquals("Ana", usuario.getNombre());
        assertEquals("ana@mail.com", usuario.getCorreo());
        assertEquals("clave", usuario.getContrasena());
        assertEquals("Estudiante", usuario.getRol());

        usuario.setNombre("Maria");
        usuario.setCorreo("maria@mail.com");
        usuario.setContrasena("nuevaClave");
        usuario.setRol("Estudiante");

        assertEquals("Maria", usuario.getNombre());
        assertEquals("maria@mail.com", usuario.getCorreo());
        assertEquals("nuevaClave", usuario.getContrasena());
        assertEquals("Estudiante", usuario.getRol());
    }

    @Test
    public void testVerificarCredenciales() {
        Estudiante usuario = new Estudiante("Luis", "luis@mail.com", "clave123", "Estudiante", "Derecho");
        assertTrue(usuario.verificarCredenciales("luis@mail.com", "clave123"));
        assertTrue(usuario.verificarCredenciales("LUIS@mail.com", "clave123")); // Ignora mayúsculas en correo
        assertFalse(usuario.verificarCredenciales("otro@mail.com", "clave123"));
        assertFalse(usuario.verificarCredenciales("luis@mail.com", "otraClave"));
    }

    @Test
    public void testActualizarInformacion() {
        Estudiante usuario = new Estudiante("Pedro", "pedro@mail.com", "clave", "Estudiante", "Arquitectura");
        usuario.actualizarInformacion("Pedro Nuevo", "claveNueva");
        assertEquals("Pedro Nuevo", usuario.getNombre());
        assertEquals("claveNueva", usuario.getContrasena());
    }

    @Test
    public void testCalcularMulta() {
        Estudiante usuario = new Estudiante("Ana", "ana@mail.com", "clave", "Estudiante", "Ingeniería");
        assertEquals(300.0, usuario.calcularMulta(3), 0.01); // 3 * 100
    }
}
