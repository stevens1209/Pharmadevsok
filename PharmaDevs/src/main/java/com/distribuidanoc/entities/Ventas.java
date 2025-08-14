package com.distribuidanoc.entities;

import java.util.Date;

public class Ventas {

    private int idVentas;

    private Date Fechaventa;

    private String Formapago;

    private String Totalventa;


    private Cliente cliente;

    private Facturaventa facturaventa;

    public Ventas() {
    }

    public Ventas(int idVentas, Date fechaventa, String formapago, String totalventa) {
        this.idVentas = idVentas;
        Fechaventa = fechaventa;
        Formapago = formapago;
        Totalventa = totalventa;
        cliente = cliente;
        facturaventa = facturaventa;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public Date getFechaventa() {
        return Fechaventa;
    }

    public void setFechaventa(Date fechaventa) {
        Fechaventa = fechaventa;
    }

    public String getFormapago() {
        return Formapago;
    }

    public void setFormapago(String formapago) {
        Formapago = formapago;
    }

    public String getTotalventa() {
        return Totalventa;
    }

    public void setTotalventa(String totalventa) {
        Totalventa = totalventa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Facturaventa getFacturaventa() {
        return facturaventa;
    }

    public void setFacturaventa(Facturaventa facturaventa) {
        this.facturaventa = facturaventa;
    }

    @Override
    public String toString() {
        return "Ventas{" +'\'' +
                "idVentas=" + idVentas +'\'' +
                ", Fechaventa=" + Fechaventa +'\'' +
                ", Formapago='" + Formapago + '\'' +
                ", Totalventa='" + Totalventa + '\'' +
                ", Cliente='" + cliente + '\'' +
                ", Factura venta='" + facturaventa + '\'' +
                '}';
    }
}
