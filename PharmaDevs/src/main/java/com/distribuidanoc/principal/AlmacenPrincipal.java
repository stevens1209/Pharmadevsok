package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Almacen;
import com.distribuidanoc.entities.Producto;

public class AlmacenPrincipal {
    public static void main(String[] Args){
        Almacen almacen = new Almacen(1,"1000","Puembo");

        Producto producto = new Producto(1, "Blinker Plus", "Antiparasitario para perros, externo\n" +
                "Tableta masticable", "Tabletas", "120 Und",10.99F);

        almacen.setProducto(producto);

        System.out.println(almacen.toString());
    }
}
