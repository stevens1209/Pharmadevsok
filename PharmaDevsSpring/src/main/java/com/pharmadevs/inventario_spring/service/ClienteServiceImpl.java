package com.pharmadevs.inventario_spring.service;


import com.pharmadevs.inventario_spring.dao.ClienteRepositorio;
import com.pharmadevs.inventario_spring.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> findAll(){
        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente findById(int id){
        return clienteRepositorio.findById(id).orElse(null);
    }

    @Override
    public Cliente findOne(int id){
       Optional<Cliente> cliente = clienteRepositorio.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente){
        return clienteRepositorio.save(cliente);
    }

    @Override
    public Cliente update(int id, Cliente clienteNuevo){
        Cliente clienteExistente = findOne(id);

        if (clienteExistente == null){
            return null;
        }

        clienteExistente.setDireccion(clienteNuevo.getDireccion());
        clienteExistente.setEmail(clienteNuevo.getEmail());
        clienteExistente.setNombre(clienteNuevo.getNombre());
        clienteExistente.setRuc(clienteNuevo.getRuc());
        clienteExistente.setTelefono(clienteNuevo.getTelefono());

        return clienteRepositorio.save(clienteExistente);
    }

    @Override
    public void delete(int id){
        if (clienteRepositorio.existsById(id)){
            clienteRepositorio.deleteById(id);
        }
    }
}
