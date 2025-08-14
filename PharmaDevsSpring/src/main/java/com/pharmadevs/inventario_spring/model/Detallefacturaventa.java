package com.pharmadevs.inventario_spring.model;


import jakarta.persistence.*;

@Entity
@Table(name = "detalle_factura_venta")
public class Detallefacturaventa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Detalle_Venta")
    private int idDetalleventa;

    @Column(name ="Cantidad")
    private int Cantidad;

    @Column(name ="Precio_Unitario")
    private float Preciounitario;

    @Column(name ="IVA")
    private String Iva;

    @Column(name ="Subtotal")
    private Float Subtotal;

    @ManyToOne
    @JoinColumn(name = "ID_Producto")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "ID_Factura_Venta")
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
