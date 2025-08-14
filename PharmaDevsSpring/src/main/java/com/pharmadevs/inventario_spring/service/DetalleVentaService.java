package com.pharmadevs.inventario_spring.service;

    import com.pharmadevs.inventario_spring.model.Detallefacturaventa;

import java.util.List;

public interface DetalleVentaService {
    List<Detallefacturaventa> findAll();
    Detallefacturaventa findById(int id);
    Detallefacturaventa findOne(int id);
    Detallefacturaventa save(Detallefacturaventa detallefacturaventa);
    Detallefacturaventa update(int id, Detallefacturaventa detallefacturaventa);
    void delete(int id);
}
