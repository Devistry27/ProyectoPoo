package model;

import java.util.HashMap;
import model.Usuario; 

public class AuthService {

    private HashMap<String, Usuario> usuariosRegistrados = new HashMap<>();

    // Registro manual de usuarios
    public void registrarUsuario(Usuario usuario) {
        usuariosRegistrados.put(usuario.getCorreo(), usuario);
    }

    // Login básico
    public Usuario login(String correo, String contrasena) {
        Usuario usuario = usuariosRegistrados.get(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            System.out.println("Autenticacion exitosa para: " + usuario.getNombre());
            return usuario;
        } else {
            System.out.println("Correo o contrasena incorrectos.");
            return null;
        }
    }
}
