package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Facturaventa;

import java.util.List;

public interface FacturaVentaService {
    List<Facturaventa> findAll();
    Facturaventa findById(int id);
    Facturaventa findOne(int id);
    Facturaventa save(Facturaventa facturaventa);
    Facturaventa update(int id, Facturaventa facturaventa);
    void delete(int id);
}
