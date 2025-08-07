package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Almacen;

import java.util.List;

public interface AlmacenService {
    List <Almacen> findAll();
    Almacen findById(int id);
    Almacen findOne(int id);
    Almacen save(Almacen almacen);
    Almacen update(int id, Almacen almacen);
    void delete(int id);
}
