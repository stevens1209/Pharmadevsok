package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Produccion;
import com.distribuidanoc.entities.Producto;

import java.util.Date;

public class ProduccionPrincipal {
    public static void main(String[] Args){
        Produccion produccion = new Produccion(1,new Date(),new Date(),"0001","1000");

        Producto producto = new Producto(1, "Blinker Plus", "Antiparasitario para perros, externo Tableta masticable", "Tabletas", "Unidades",10.99F);

        produccion.setProducto(producto);

        System.out.println(produccion.toString());
    }
}
