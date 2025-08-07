package com.distribuidanoc.entities;


import java.util.Date;

public class Produccion {

    private int idProduccion;

    private Date Fechaproduccion;

    private Date Fechavencimiento;

    private String Lote;

    private String Cantidadproducida;

    private Producto producto;

    public Produccion() {
    }

    public Produccion(int idProduccion, Date fechaproduccion, Date fechavencimiento, String lote, String cantidadproducida) {
        this.idProduccion = idProduccion;
        Fechaproduccion = fechaproduccion;
        Fechavencimiento = fechavencimiento;
        Lote = lote;
        Cantidadproducida = cantidadproducida;
        producto = producto;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public Date getFechaproduccion() {
        return Fechaproduccion;
    }

    public void setFechaproduccion(Date fechaproduccion) {
        Fechaproduccion = fechaproduccion;
    }

    public Date getFechavencimiento() {
        return Fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        Fechavencimiento = fechavencimiento;
    }

    public String getLote() {
        return Lote;
    }

    public void setLote(String lote) {
        Lote = lote;
    }

    public String getCantidadproducida() {
        return Cantidadproducida;
    }

    public void setCantidadproducida(String cantidadproducida) {
        Cantidadproducida = cantidadproducida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Produccion{" +
                "idProduccion=" + idProduccion +'\'' +
                ", Fechaproduccion=" + Fechaproduccion +'\'' +
                ", Fechavencimiento=" + Fechavencimiento +'\'' +
                ", Lote='" + Lote + '\'' +
                ", Cantidadproducida='" + Cantidadproducida + '\'' +
                ", Producto='" + producto + '\'' +
                '}';
    }
}
