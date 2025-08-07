package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Facturaventa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaVentaRepositorio extends JpaRepository<Facturaventa,Integer> {
}
