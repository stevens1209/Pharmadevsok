package com.pharmadevs.inventario_spring.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "factura_compra")
public class Facturacompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Factura_Compra")
    private int idFacturacompra;

    @Column(name ="Fecha")
    private Date Fecha;

    @Column(name ="Monto_Total")
    private String Montototal;

    //Patron de inyeccion de dependecias
    @ManyToOne
    @JoinColumn(name = "ID_Proveedor")
    private Proveedor proveedor;

    public Facturacompra() {
    }

    public Facturacompra(int idFacturacompra, Date fecha, String montototal) {
        this.idFacturacompra = idFacturacompra;
        Fecha = fecha;
        Montototal = montototal;
        proveedor = proveedor;
    }

    public int getIdFacturacompra() {
        return idFacturacompra;
    }

    public void setIdFacturacompra(int idFacturacompra) {
        this.idFacturacompra = idFacturacompra;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Facturacompra{" +
                "idFacturacompra=" + idFacturacompra +'\'' +
                ", Fecha=" + Fecha +'\'' +
                ", Montototal='" + Montototal + '\'' +
                ", Proveedor='" + proveedor + '\'' +
                '}';
    }
}