// dao/PrestamoDAO.java
package DAO;

import model.Prestamo;
import java.util.*;

public class PrestamoDao {
    private final Map<Integer, Prestamo> tabla = new HashMap<>();
    private int nextId = 1;

    public void save(Prestamo prestamo) {
        // asigna el ID internamente al guardar
        try {
            var field = Prestamo.class.getDeclaredField("id");
            field.setAccessible(true);
            field.setInt(prestamo, nextId);
        } catch (Exception ignored) {
        }
        tabla.put(nextId, prestamo);
        nextId++;
    }

    public Prestamo buscarPrestamoPorISBN(String isbn) {
        for (Prestamo prestamo : tabla.values()) {
            if (prestamo.getLibro().getIsbn().equals(isbn)) {
                return prestamo;
            }
        }
        return null;
    }

    public List<Prestamo> findAll() {
        return new ArrayList<>(tabla.values());
    }

    public void update(Prestamo prestamo) {
        tabla.put(prestamo.getId(), prestamo);
    }

    public void delete(int id) {
        tabla.remove(id);
    }
}
