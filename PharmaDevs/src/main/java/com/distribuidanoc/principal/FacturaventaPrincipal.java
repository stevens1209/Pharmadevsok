package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Cliente;
import com.distribuidanoc.entities.Facturaventa;

import java.util.Date;

public class FacturaventaPrincipal {
    public static void main(String[] Args){
        Facturaventa facturaventa = new Facturaventa(1,new Date(),"126.385");
        Cliente cliente = new Cliente(1,"Soldis","Quito","0999999998","example1@example.com","171717171718001");

        facturaventa.setCliente(cliente);

        System.out.println(facturaventa.toString());
    }
}
