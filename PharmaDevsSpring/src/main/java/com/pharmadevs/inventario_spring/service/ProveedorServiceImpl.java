package com.pharmadevs.inventario_spring.service;


import com.pharmadevs.inventario_spring.dao.ProveedorRepositorio;
import com.pharmadevs.inventario_spring.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    ProveedorRepositorio proveedorRepositorio;

    @Override
    public List<Proveedor> findAll(){
        return proveedorRepositorio.findAll();
    }

    @Override
    public Proveedor findById(int id){
        return proveedorRepositorio.findById(id).orElse(null);
    }

    @Override
    public Proveedor findOne(int id){
        Optional<Proveedor> proveedor = proveedorRepositorio.findById(id);
        return proveedor.orElse(null);
    }

    @Override
    public Proveedor save(Proveedor proveedor){
        return proveedorRepositorio.save(proveedor);
    }

    @Override
    public Proveedor update(int id, Proveedor proveedorNuevo){
        Proveedor proveedorExistente = findOne(id);

        if (proveedorExistente == null){
            return null;
        }

        proveedorExistente.setDireccion(proveedorNuevo.getDireccion());
        proveedorExistente.setEmail(proveedorNuevo.getEmail());
        proveedorExistente.setNombre(proveedorNuevo.getNombre());
        proveedorExistente.setRuc(proveedorNuevo.getRuc());
        proveedorExistente.setTelefono(proveedorNuevo.getTelefono());


        return proveedorRepositorio.save(proveedorExistente);
    }

    @Override
    public void delete(int id){
        if (proveedorRepositorio.existsById(id)){
            proveedorRepositorio.deleteById(id);
        }
    }
}
