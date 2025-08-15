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
    private Date fechaproduccion;

    @Column(name ="Fecha_Vencimiento")
    private Date fechavencimiento;

    @Column(name ="Lote")
    private String lote;

    @Column(name ="Cantidad_Producida")
    private int cantidadproducida;

    @Column(name="imagen")
    private String imagen;


    @ManyToOne
    @JoinColumn(name = "ID_Producto")
    private Producto producto;

    public Produccion() {
    }

    public Produccion(int idProduccion, Date fechaproduccion, Date fechavencimiento, String lote, int cantidadproducida, String imagen, Producto producto) {
        this.idProduccion = idProduccion;
        this.fechaproduccion = fechaproduccion;
        this.fechavencimiento = fechavencimiento;
        this.lote = lote;
        this.cantidadproducida = cantidadproducida;
        this.imagen = imagen;
        this.producto = producto;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public Date getfechaproduccion() {
        return fechaproduccion;
    }

    public void setFechaproduccion(Date fechaproduccion) {
        this.fechaproduccion = fechaproduccion;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getCantidadproducida() {
        return cantidadproducida;
    }

    public void setCantidadproducida(int cantidadproducida) {
        this.cantidadproducida = cantidadproducida;
    }

    public String getImagen(){
        return imagen;
    }

    public void setImagen(String imagen){
        this.imagen=imagen;
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
                ", fechaproduccion=" + fechaproduccion +'\'' +
                ", fechavencimiento=" + fechavencimiento +'\'' +
                ", lote='" + lote + '\'' +
                ", cantidadproducida='" + cantidadproducida + '\'' +
                ",imagen='" + imagen +'\''+
                ", producto='" + producto + '\'' +
                '}';
    }
}