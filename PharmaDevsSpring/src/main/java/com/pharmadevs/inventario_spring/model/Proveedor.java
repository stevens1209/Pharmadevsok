package com.pharmadevs.inventario_spring.model;


import jakarta.persistence.*;

@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_Proveedor")
    private int idProveedor;

    @Column(name ="Nombre")
    private String Nombre;

    @Column(name ="Dirección")
    private String Direccion;

    @Column(name ="Teléfono")
    private String Telefono;

    @Column(name ="Email")
    private String Email;

    @Column(name ="RUC")
    private String Ruc;


    public Proveedor() {
    }

    public Proveedor(int idProveedor, String nombre, String direccion, String telefono, String email, String ruc) {
        this.idProveedor = idProveedor;
        Nombre = nombre;
        Direccion = direccion;
        Telefono = telefono;
        Email = email;
        Ruc = ruc;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }



    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", Nombre='" + Nombre + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Email='" + Email + '\'' +
                ", Ruc='" + Ruc + '\'' +
                '}';
    }
}
