package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Produccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduccionRepositorio extends JpaRepository<Produccion, Integer> {
}
