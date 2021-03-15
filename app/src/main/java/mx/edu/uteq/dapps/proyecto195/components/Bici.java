package mx.edu.uteq.dapps.proyecto195.components;

/**
 * Creamos una clase con los atributos necesarios que deseamos
 * mostrar en nuestro ListView personalizado
 *
 */
public class Bici {

    private int biciId;
    private String modelo;
    private String categoria;
    private String imagen;
    private int calificacion;

    public int getBiciId() {
        return biciId;
    }

    public void setBiciId(int biciId) {
        this.biciId = biciId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
