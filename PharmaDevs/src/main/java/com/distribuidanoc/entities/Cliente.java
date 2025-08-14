package com.distribuidanoc.entities;

public class Cliente {
    private int idCliente;

    private String Nombre;

    private String Direccion;

    private String Telefono;

    private String Email;

    private String Ruc;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String direccion, String telefono, String email, String ruc) {
        this.idCliente = idCliente;
        Nombre = nombre;
        Direccion = direccion;
        Telefono = telefono;
        Email = email;
        Ruc = ruc;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", Nombre='" + Nombre + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Email='" + Email + '\'' +
                ", Ruc='" + Ruc + '\'' +
                '}';
    }
}
