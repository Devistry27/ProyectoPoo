package ProyectoPoo.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Material;

public class MaterialTest {

    @Test
    public void testCrearMaterial() {
        Material material = new Material("Libro de POO", "MAT001");
        assertEquals("Libro de POO", material.getTitulo());
        assertEquals("MAT001", material.getCodigo());
    }

    @Test
    public void testCrearMaterialConValoresNulos() {
        Material material = new Material(null, null);
        assertEquals("Sin titulo", material.getTitulo());
        assertEquals("Sin codigo", material.getCodigo());
    }

    @Test
    public void testSetters() {
        Material material = new Material("Titulo", "Codigo");
        material.setTitulo("Nuevo Titulo");
        material.setCodigo("NuevoCodigo");
        assertEquals("Nuevo Titulo", material.getTitulo());
        assertEquals("NuevoCodigo", material.getCodigo());
    }
}
