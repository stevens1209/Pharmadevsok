package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegaRepositorio extends JpaRepository<Bodega, Integer> {
}
