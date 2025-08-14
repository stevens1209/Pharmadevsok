package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Cliente;
import com.distribuidanoc.entities.Facturaventa;
import com.distribuidanoc.entities.Ventas;

import java.util.Date;

public class VentasPrincipal {
    public static void  main(String[] Args){
        Ventas ventas = new Ventas(1,new Date(),"Efectivo","126.385");

        Cliente cliente = new Cliente(1,"Soldis","Quito","0999999998","example1@example.com","171717171718001");
        Facturaventa facturaventa = new Facturaventa(1,new Date(),"126.385");


        ventas.setCliente(cliente);
        ventas.setFacturaventa(facturaventa);

        System.out.println(ventas.toString());
    }
}
