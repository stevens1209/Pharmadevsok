package com.pharmadevs.inventario_spring.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "factura_venta")
public class Facturaventa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Factura_Venta")
    private int idFacturaventa;

    @Column(name ="Fecha")
    private Date Fecha;

    @Column(name ="Monto_Total")
    private String Montototal;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente")
    private Cliente cliente;

    public Facturaventa() {
    }

    public Facturaventa(int idFacturaventa, Date fecha, String montototal) {
        this.idFacturaventa = idFacturaventa;
        Fecha = fecha;
        Montototal = montototal;
        cliente = cliente;
    }

    public int getIdFacturaventa() {
        return idFacturaventa;
    }

    public void setIdFacturaventa(int idFacturaventa) {
        this.idFacturaventa = idFacturaventa;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getMontototal() {
        return Montototal;
    }

    public void setMontototal(String montototal) {
        Montototal = montototal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Facturaventa{" +'\'' +
                "idFacturaventa=" + idFacturaventa +'\'' +
                ", Fecha=" + Fecha +'\'' +
                ", Montototal='" + Montototal + '\'' +
                ", Cliente='" + cliente + '\'' +
                '}';
    }
}
