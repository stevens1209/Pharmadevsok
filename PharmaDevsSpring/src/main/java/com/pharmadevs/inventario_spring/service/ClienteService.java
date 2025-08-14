package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(int id);
    Cliente findOne(int id);
    Cliente save(Cliente cliente);
    Cliente update(int id, Cliente cliente);
    void delete(int id);

}
