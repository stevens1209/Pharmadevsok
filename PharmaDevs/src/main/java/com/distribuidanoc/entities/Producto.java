package com.distribuidanoc.entities;

public class Producto {

    private int idProducto;

    private String Nombre;

    private String Descripcion;

    private String UnidadMedida;

    private String Categoria;

    private Float Preciobase;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String descripcion, String categoria, String unidadMedida, Float preciobase) {
        this.idProducto = idProducto;
        Nombre = nombre;
        Descripcion = descripcion;
        Categoria = categoria;
        UnidadMedida = unidadMedida;
        Preciobase = preciobase;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        UnidadMedida = unidadMedida;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public Float getPreciobase() {
        return Preciobase;
    }

    public void setPreciobase(Float preciobase) {
        Preciobase = preciobase;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", Nombre='" + Nombre + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", UnidadMedida='" + UnidadMedida + '\'' +
                ", Categoria='" + Categoria + '\'' +
                ", Preciobase=" + Preciobase +
                '}';
    }
}
