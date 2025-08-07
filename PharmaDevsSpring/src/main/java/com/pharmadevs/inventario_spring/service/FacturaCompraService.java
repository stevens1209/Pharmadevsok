package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Facturacompra;
import com.pharmadevs.inventario_spring.model.Facturaventa;

import java.util.List;

public interface FacturaCompraService {
    List<Facturacompra> findAll();
    Facturacompra findById(int id);
    Facturacompra findOne(int id);
    Facturacompra save(Facturacompra facturacompra);
    Facturacompra update(int id, Facturacompra facturacompra);
    void delete(int id);
}
