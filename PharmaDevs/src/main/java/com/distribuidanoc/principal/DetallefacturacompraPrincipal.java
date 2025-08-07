package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Detallefacturaventa;
import com.distribuidanoc.entities.Facturaventa;
import com.distribuidanoc.entities.Producto;

import java.util.Date;

public class DetallefacturacompraPrincipal {
    public static void main(String[] Args){
        Detallefacturaventa Detallefacturaventa = new Detallefacturaventa(1,150,0.35F,"15%",42.00F);

        Producto producto = new Producto(1, "Blinker Plus", "Antiparasitario para perros, externo Tableta masticable", "Tabletas", "120 Und",10.99F);
        Facturaventa Facturaventa = new Facturaventa(1,new Date(),"48.3");


        Detallefacturaventa.setProducto(producto);
        Detallefacturaventa.setFacturaventa(Facturaventa);

        System.out.println(Detallefacturaventa.toString());
    }
}
