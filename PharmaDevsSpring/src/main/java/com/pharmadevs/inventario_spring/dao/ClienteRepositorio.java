package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente,Integer> {

}
