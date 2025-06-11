package DAO;

import java.util.*;
import model.Multa;

public class MultaDao {

    // Ahora la clave es String
    private final Map<String, Multa> tabla = new HashMap<>();

    /** Guarda la multa usando el ID que ya trae el objeto */
    public void save(Multa multa) {
        tabla.put(multa.getId(), multa);
    }

    /** Busca todas las multas de un usuario */
    public List<Multa> findByUsuario(model.Usuario usuario) {
        List<Multa> resultado = new ArrayList<>();
        for (Multa multa : tabla.values()) {
            if (multa.getUsuario().equals(usuario)) {
                resultado.add(multa);
            }
        }
        return resultado;
    }

    /** Devuelve todas las multas */
    public List<Multa> findAll() {
        return new ArrayList<>(tabla.values());
    }

    /** Reemplaza la entrada existente bajo el mismo ID */
    public void update(Multa multa) {
        tabla.put(multa.getId(), multa);
    }

    /** Elimina por ID (String) */
    public void delete(String id) {
        tabla.remove(id);
    }
}
