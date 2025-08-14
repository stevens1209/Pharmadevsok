package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Detallefacturacompra;
import com.pharmadevs.inventario_spring.model.Detallefacturaventa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepositorio extends JpaRepository<Detallefacturacompra,Integer> {
}
