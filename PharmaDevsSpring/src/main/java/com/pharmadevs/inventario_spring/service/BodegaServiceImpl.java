package com.pharmadevs.inventario_spring.service;


import com.pharmadevs.inventario_spring.dao.BodegaRepositorio;
import com.pharmadevs.inventario_spring.model.Bodega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaServiceImpl implements BodegaService {
    @Autowired
    BodegaRepositorio bodegaRepositorio;

    @Override
    public List<Bodega> findAll(){
        return bodegaRepositorio.findAll();
    }

    @Override
    public Bodega findById(int id){
        return bodegaRepositorio.findById(id).orElse(null);
    }

    @Override
    public Bodega findOne(int id){
        Optional<Bodega> bodega = bodegaRepositorio.findById(id);
        return bodega.orElse(null);
    }

    @Override
    public Bodega save(Bodega bodega){
        return bodegaRepositorio.save(bodega);
    }

    @Override
    public Bodega update(int id, Bodega bodegaNuevo){
        Bodega bodegaExistente = findOne(id);

        if (bodegaExistente == null){
            return null;
        }

        bodegaExistente.setCantidad(bodegaNuevo.getCantidad());
        bodegaExistente.setFecharecepcion(bodegaNuevo.getFecharecepcion());
        bodegaExistente.setProveedor(bodegaNuevo.getProveedor());
        bodegaExistente.setNombrematerial(bodegaNuevo.getNombrematerial());
        return bodegaRepositorio.save(bodegaExistente);
    }

    @Override
    public void delete(int id){
        if (bodegaRepositorio.existsById(id)){
            bodegaRepositorio.deleteById(id);
        }
    }
}
