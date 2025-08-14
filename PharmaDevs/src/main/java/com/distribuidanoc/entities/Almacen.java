package com.distribuidanoc.entities;


public class Almacen {

    private int idAlmacen;

    private String Stock;

    private String Ubicacion;

    private Producto producto;

    public Almacen() {
    }

    public Almacen(int idAlmacen, String stock, String ubicacion) {
        this.idAlmacen = idAlmacen;
        Stock = stock;
        Ubicacion = ubicacion;
        producto = producto;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Almacen{" +
                "idAlmacen=" + idAlmacen +'\'' +
                ", Stock='" + Stock + '\'' +
                ", Ubicacion='" + Ubicacion + '\'' +
                ", Producto='" + producto + '\'' +
                '}';
    }
}
