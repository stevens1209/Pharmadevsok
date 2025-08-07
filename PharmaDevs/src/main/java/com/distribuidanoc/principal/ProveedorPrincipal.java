package com.distribuidanoc.principal;


import com.distribuidanoc.entities.Proveedor;

public class ProveedorPrincipal {
    public static void main(String[] Args) {
        Proveedor proveedor = new Proveedor(1, "Imprenta Mariscal", "Pifo", "023941700", "example@example.com", "171234567001");

        System.out.println(proveedor);
    }
}
