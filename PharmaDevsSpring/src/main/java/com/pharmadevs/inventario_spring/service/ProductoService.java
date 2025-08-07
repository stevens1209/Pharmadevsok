package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(int id);
    Producto findOne(int id);
    Producto save(Producto producto);
    Producto update(int id, Producto producto);
    void delete(int id);
}
