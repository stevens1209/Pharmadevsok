package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Ventas;

import java.util.List;

public interface VentasService {
    List<Ventas> findAll();
    Ventas findById(int id);
    Ventas findOne(int id);
    Ventas save(Ventas ventas);
    Ventas update(int id, Ventas ventas);
    void delete(int id);
}
