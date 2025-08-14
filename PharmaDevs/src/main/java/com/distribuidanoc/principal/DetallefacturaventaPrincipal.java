package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Detallefacturaventa;
import com.distribuidanoc.entities.Facturaventa;
import com.distribuidanoc.entities.Producto;

import java.util.Date;

public class DetallefacturaventaPrincipal {
    public static void main(String[] Args){
        Detallefacturaventa detallefacturaventa = new Detallefacturaventa(1,10,10.99F,"15%",109.9F);

        Producto producto = new Producto(1, "Blinker Plus", "Antiparasitario para perros, externo Tableta masticable", "Tabletas", "120 Und",10.99F);
        Facturaventa facturaventa = new Facturaventa(1,new Date(),"126.385");

        detallefacturaventa.setProducto(producto);
        detallefacturaventa.setFacturaventa(facturaventa);

        System.out.println(detallefacturaventa.toString());
    }
}
