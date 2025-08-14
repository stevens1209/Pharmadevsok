package com.distribuidanoc.entities;

public class Detallefacturaventa {

    private int idDetalleventa;

    private int Cantidad;

    private float Preciounitario;

    private String Iva;

    private Float Subtotal;

    private Producto producto;

    private Facturaventa facturaventa;


    public Detallefacturaventa() {
    }

    public Detallefacturaventa(int idDetalleventa, int cantidad, float preciounitario, String iva, Float subtotal) {
        this.idDetalleventa = idDetalleventa;
        Cantidad = cantidad;
        Preciounitario = preciounitario;
        Iva = iva;
        Subtotal = subtotal;
        producto = producto;
        facturaventa = facturaventa;
    }

    public int getIdDetalleventa() {
        return idDetalleventa;
    }

    public void setIdDetalleventa(int idDetalleventa) {
        this.idDetalleventa = idDetalleventa;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public float getPreciounitario() {
        return Preciounitario;
    }

    public void setPreciounitario(float preciounitario) {
        Preciounitario = preciounitario;
    }

    public String getIva() {
        return Iva;
    }

    public void setIva(String iva) {
        Iva = iva;
    }

    public Float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Float subtotal) {
        Subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Facturaventa getFacturaventa() {
        return facturaventa;
    }

    public void setFacturaventa(Facturaventa facturaventa) {
        this.facturaventa = facturaventa;
    }

    @Override
    public String toString() {
        return "Detallefacturaventa{" +'\'' +
                "idDetalleventa=" + idDetalleventa +'\'' +
                ", Cantidad=" + Cantidad +'\'' +
                ", Preciounitario=" + Preciounitario +'\'' +
                ", Iva='" + Iva + '\'' +
                ", Subtotal=" + Subtotal +'\'' +
                ", Producto=" + producto +'\'' +
                ", Facturaventa=" + facturaventa +'\'' +
                '}';
    }
}
