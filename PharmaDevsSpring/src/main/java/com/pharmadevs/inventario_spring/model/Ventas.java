package com.pharmadevs.inventario_spring.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Venta")
    private int idVentas;

    @Column(name ="Fecha_Venta")
    private Date Fechaventa;

    @Column(name ="Forma_Pago")
    private String Formapago;

    @Column(name ="Total_Venta")
    private String Totalventa;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "ID_Factura_Venta")
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
