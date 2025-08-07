package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepositorio extends JpaRepository<Ventas, Integer> {
}
