package com.pharmadevs.inventario_spring.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "produccion")
public class Produccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Produccion")
    private int idProduccion;

    @Column(name ="Fecha_Producción")
    private Date Fechaproduccion;

    @Column(name ="Fecha_Vencimiento")
    private Date Fechavencimiento;

    @Column(name ="Lote")
    private String Lote;

    @Column(name ="Cantidad_Producida")
    private String Cantidadproducida;


    @ManyToOne
    @JoinColumn(name = "ID_Producto")
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
