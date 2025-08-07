package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepositorio extends JpaRepository<Almacen, Integer> {
}
