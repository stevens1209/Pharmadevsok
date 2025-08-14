package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Bodega;
import com.distribuidanoc.entities.Proveedor;

import java.util.Date;

public class BodegaPrincipal {
    public static void main(String[] Args){
        Bodega bodega = new Bodega(1,"Prospecto Blinker Plus",120,new Date());

        Proveedor proveedor = new Proveedor(1, "Imprenta Mariscal", "Pifo", "023941700", "example@example.com", "171234567001");

        bodega.setProveedor(proveedor);

        System.out.println(bodega.toString());
    }
}
