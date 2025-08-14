package com.pharmadevs.inventario_spring.model;


import jakarta.persistence.*;

@Entity
@Table(name = "detalle_factura_compra")
public class Detallefacturacompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Detalle_Compra")
    private int idDetallecompra;

    @Column(name ="Cantidad")
    private String Cantidad;

    @Column(name ="Precio_Unitario")
    private Float Preciounitario;

    @Column(name ="IVA")
    private String Iva;

    @Column(name ="Subtotal")
    private Float Subtotal;

    //Patron de inyeccion de dependecias
    @ManyToOne
    @JoinColumn(name = "ID_Producto")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "ID_Factura_Compra")
    private Facturacompra facturacompra;

    public Detallefacturacompra() {
    }

    public Detallefacturacompra(int idDetallecompra, String cantidad, Float preciounitario, String iva, Float subtotal) {
        this.idDetallecompra = idDetallecompra;
        Cantidad = cantidad;
        Preciounitario = preciounitario;
        Iva = iva;
        Subtotal = subtotal;
        producto = producto;
        facturacompra = facturacompra;
    }

    public int getIdDetallecompra() {
        return idDetallecompra;
    }

    public void setIdDetallecompra(int idDetallecompra) {
        this.idDetallecompra = idDetallecompra;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public Float getPreciounitario() {
        return Preciounitario;
    }

    public void setPreciounitario(Float preciounitario) {
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


    public Facturacompra getFacturacompra() {
        return facturacompra;
    }

    public void setFacturacompra(Facturacompra facturacompra) {
        this.facturacompra = facturacompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Detallefacturacompra{" +
                "idDetallecompra=" + idDetallecompra + '\'' +
                ", Cantidad='" + Cantidad + '\'' +
                ", Preciounitario=" + Preciounitario +'\'' +
                ", Iva='" + Iva + '\'' +
                ", Subtotal=" + Subtotal +'\'' +
                ", Producto=" + producto +'\'' +
                ", Factura compra=" + facturacompra +'\'' +
                '}';
    }
}