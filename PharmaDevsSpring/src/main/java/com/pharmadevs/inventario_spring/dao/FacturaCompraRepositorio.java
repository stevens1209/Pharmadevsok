package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Facturacompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaCompraRepositorio extends JpaRepository<Facturacompra, Integer> {
}
