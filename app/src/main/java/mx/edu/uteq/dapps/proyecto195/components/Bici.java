package mx.edu.uteq.dapps.proyecto195.components;

/**
 * Creamos una clase con los atributos necesarios que deseamos
 * mostrar en nuestro ListView personalizado
 *
 * POJO (Plain Old Java Object)
 */
public class Bici {

    private int biciId;
    private String imagen;
    private String modelo;
    private String categoria;
    private int calificacion;
    private double precio;
    private String descripcion;
    private int activo;

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

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
