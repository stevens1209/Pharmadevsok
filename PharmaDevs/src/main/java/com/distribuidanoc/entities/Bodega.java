package com.distribuidanoc.entities;

import java.util.Date;

public class Bodega {

    private int idBodega;
    private String Nombrematerial;

    private int Cantidad;

    private Date Fecharecepcion;

    //Patron de inyeccion de dependecias
    private Proveedor proveedor;

    public Bodega() {
    }

    public Bodega(int idBodega, String nombrematerial, int cantidad, Date fecharecepcion) {
        this.idBodega = idBodega;
        Nombrematerial = nombrematerial;
        Cantidad = cantidad;
        Fecharecepcion = fecharecepcion;
        proveedor = proveedor;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public String getNombrematerial() {
        return Nombrematerial;
    }

    public void setNombrematerial(String nombrematerial) {
        Nombrematerial = nombrematerial;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Date getFecharecepcion() {
        return Fecharecepcion;
    }

    public void setFecharecepcion(Date fecharecepcion) {
        Fecharecepcion = fecharecepcion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "idBodega=" + idBodega +
                ", Nombrematerial='" + Nombrematerial + '\'' +
                ", Cantidad=" + Cantidad +'\'' +
                ", Fecharecepcion=" + Fecharecepcion +'\'' +
                ", Proveedor=" + proveedor +'\'' +
                '}';
    }
}
