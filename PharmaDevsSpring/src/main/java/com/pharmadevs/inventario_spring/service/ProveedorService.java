package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Proveedor;

import java.util.List;

public interface ProveedorService {
    List<Proveedor> findAll();
    Proveedor findById(int id);
    Proveedor findOne(int id);
    Proveedor save(Proveedor proveedor);
    Proveedor update(int id, Proveedor proveedor);
    void delete(int id);
}
