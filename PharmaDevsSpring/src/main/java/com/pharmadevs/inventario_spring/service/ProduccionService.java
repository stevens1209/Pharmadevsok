package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Produccion;

import java.util.List;

public interface ProduccionService {
    List<Produccion> findAll();
    Produccion findById(int id);
    Produccion findOne(int id);
    Produccion save(Produccion produccion);
    Produccion update(int id, Produccion produccion);
    void delete(int id);
}
