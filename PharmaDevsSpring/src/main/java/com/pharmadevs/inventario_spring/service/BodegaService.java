package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Bodega;

import java.util.List;

public interface BodegaService {
    List<Bodega> findAll();
    Bodega findById(int id);
    Bodega findOne(int id);
    Bodega save(Bodega bodega);
    Bodega update(int id, Bodega bodega);
    void delete(int id);
}
