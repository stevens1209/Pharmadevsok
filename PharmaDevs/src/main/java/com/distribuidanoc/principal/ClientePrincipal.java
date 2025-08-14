package com.distribuidanoc.principal;

import com.distribuidanoc.entities.Cliente;

public class ClientePrincipal {
    public static void main(String[] Args){
        Cliente cliente = new Cliente(1,"Soldis","Quito","0999999998","example1@example.com","171717171718001");
        System.out.println(cliente);
    }
}
