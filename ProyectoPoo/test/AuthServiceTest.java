package ProyectoPoo.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AuthServiceTest {
    @Test
    public void testValidarCredenciales() {
        // Crear una instancia del servicio de autenticación
        model.AuthService authService = new model.AuthService();

        // Registrar un usuario
        model.Usuario usuario = new model.Usuario("Juan", "@correo.com", "contrasena123", "estudiante") {
            @Override
            public int obtenerLimitePrestamo() {
                return 5; // Ejemplo de implementación
            }
        };	
    }

    @Test
    public void testLoginExitoso() {
        // Crear una instancia del servicio de autenticación
        model.AuthService authService = new model.AuthService();

        // Registrar un usuario
        model.Usuario usuario = new model.Usuario("Juan", "@correo.com", "contrasena123", "estudiante") {
            @Override
            public int obtenerLimitePrestamo() {
                return 5; // Ejemplo de implementación
            }
        };
        authService.registrarUsuario(usuario);

        // Intentar iniciar sesión con credenciales correctas
        model.Usuario resultado = authService.login("@correo.com", "contrasena123");
        assertNotNull(resultado);
    }
}
