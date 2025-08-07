package com.pharmadevs.inventario_spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "almacen")

public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Almacen" )
    private int idAlmacen;

    @Column(name = "Stock")
    private String Stock;

    @Column(name = "Ubicacion")
    private String Ubicacion;

    @ManyToOne
    @JoinColumn(name = "ID_Producto")
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
