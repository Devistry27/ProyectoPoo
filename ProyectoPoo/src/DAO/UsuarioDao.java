package DAO;

import model.Usuario;
import java.util.*;

public class UsuarioDao {

    private final Map<String, Usuario> tabla = new HashMap<>();

    public void save(Usuario usuario) {
        tabla.put(usuario.getCorreo(), usuario);
    }

    public Usuario findByCorreo(String correo) {
        return tabla.get(correo);
    }

    public List<Usuario> findAll() {
        return new ArrayList<>(tabla.values());
    }

    public void update(Usuario usuario) {
        tabla.put(usuario.getCorreo(), usuario);
    }

    public void delete(String correo) {
        tabla.remove(correo);
    }
}