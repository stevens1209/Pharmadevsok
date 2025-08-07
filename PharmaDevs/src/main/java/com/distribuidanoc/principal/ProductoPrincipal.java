package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Producto;

public class ProductoPrincipal {

    public static void main(String[] Args) {
        Producto producto = new Producto(1, "Blinker Plus", "Antiparasitario para perros, externo Tableta masticable", "Tabletas", "Unidades",10.99F);

        System.out.println(producto);
    }
}
