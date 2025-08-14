package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Detallefacturaventa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepositorio extends JpaRepository<Detallefacturaventa, Integer> {
}
