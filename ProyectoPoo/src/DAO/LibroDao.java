// dao/LibroDAO.java
package DAO;

import model.Libro;
import java.util.*;

public class LibroDao {
    private final Map<String, Libro> tabla = new HashMap<>();

    public void save(Libro libro) {
        tabla.put(libro.getIsbn(), libro);
    }

    public Libro findByIsbn(String isbn) {
        return tabla.get(isbn);
    }

    public List<Libro> findAll() {
        return new ArrayList<>(tabla.values());
    }

    public void update(Libro libro) {
        tabla.put(libro.getIsbn(), libro);
    }

    public void delete(String isbn) {
        tabla.remove(isbn);
    }
}
