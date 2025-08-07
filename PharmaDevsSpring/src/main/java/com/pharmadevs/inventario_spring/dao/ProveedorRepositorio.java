package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {
}
