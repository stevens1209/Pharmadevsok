package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Detallefacturacompra;
import com.pharmadevs.inventario_spring.model.Detallefacturaventa;

import java.util.List;


public interface DetalleCompraService {
    List<Detallefacturacompra> findAll();
    Detallefacturacompra findById(int id);
    Detallefacturacompra findOne(int id);
    Detallefacturacompra save(Detallefacturacompra detallefacturacompra);
    Detallefacturacompra update(int id, Detallefacturacompra detallefacturacompra);
    void delete(int id);
}
