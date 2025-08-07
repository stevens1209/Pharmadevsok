package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Facturaventa;
import com.distribuidanoc.entities.Proveedor;

import java.util.Date;

public class FacturacompraPrincipal {
    public static void main(String[] Args){
        Facturaventa Facturaventa = new Facturaventa(1,new Date(),"48.3");

        Proveedor proveedor = new Proveedor(1, "Imprenta Mariscal", "Pifo", "023941700", "example@example.com", "171234567001");

        Facturaventa.setProveedor(proveedor);

        System.out.println(Facturaventa.toString());
    }
}
