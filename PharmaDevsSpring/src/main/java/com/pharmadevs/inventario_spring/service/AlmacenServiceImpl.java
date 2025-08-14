package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.dao.AlmacenRepositorio;
import com.pharmadevs.inventario_spring.model.Almacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AlmacenServiceImpl implements AlmacenService{
    @Autowired
    AlmacenRepositorio almacenRepositorio;

    @Override
    public List<Almacen> findAll(){
        return almacenRepositorio.findAll();
    }

    @Override
    public Almacen findById(int id){
        return almacenRepositorio.findById(id).orElse(null);
    }

    @Override
    public Almacen findOne(int id){
        Optional<Almacen> almacen = almacenRepositorio.findById(id);
        return almacen.orElse(null);
    }

    @Override
    public Almacen save(Almacen almacen){
        return almacenRepositorio.save(almacen);
    }

    @Override
    public Almacen update(int id, Almacen almacenNuevo){
        Almacen almacenExistente = findOne(id);

        if (almacenExistente == null){
            return null;
        }

        almacenExistente.setProducto(almacenNuevo.getProducto());
        almacenExistente.setStock(almacenNuevo.getStock());
        almacenExistente.setUbicacion(almacenNuevo.getUbicacion());
        almacenExistente.setImagen(almacenNuevo.getImagen());
        return almacenRepositorio.save(almacenExistente);
    }

    @Override
    public void delete(int id){
        if (almacenRepositorio.existsById(id)){
            almacenRepositorio.deleteById(id);
        }
    }
}