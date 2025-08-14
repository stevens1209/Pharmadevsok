package com.pharmadevs.inventario_spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Producto")
    private int idProducto;

    @Column(name ="Nombre")
    private String Nombre;

    @Column(name ="Descripción")
    private String Descripcion;

    @Column(name ="Unidad_Medida")
    private String UnidadMedida;

    @Column(name ="Categoría")
    private String Categoria;

    @Column(name ="Precio_Base")
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
