package model;

public class Material {

    protected String titulo;
    protected String codigo;

    public Material(String titulo, String codigo) {
        this.titulo = (titulo != null) ? titulo : "Sin titulo";
        this.codigo = (codigo != null) ? codigo : "Sin codigo";
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
