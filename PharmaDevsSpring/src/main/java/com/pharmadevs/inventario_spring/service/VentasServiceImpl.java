package com.pharmadevs.inventario_spring.service;


import com.pharmadevs.inventario_spring.dao.VentasRepositorio;
import com.pharmadevs.inventario_spring.model.Ventas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentasServiceImpl implements VentasService{
    @Autowired
    VentasRepositorio ventasRepositorio;

    @Override
    public List<Ventas> findAll(){
        return ventasRepositorio.findAll();
    }

    @Override
    public Ventas findById(int id){
        return ventasRepositorio.findById(id).orElse(null);
    }

    @Override
    public Ventas findOne(int id){
        Optional<Ventas> ventas = ventasRepositorio.findById(id);
        return ventas.orElse(null);
    }

    @Override
    public Ventas save(Ventas ventas){
        return ventasRepositorio.save(ventas);
    }

    @Override
    public Ventas update(int id, Ventas ventasNuevo){
        Ventas ventasExitente = findOne(id);

        if (ventasExitente == null){
            return null;
        }

        ventasExitente.setFechaventa(ventasNuevo.getFechaventa());
        ventasExitente.setFormapago(ventasNuevo.getFormapago());
        ventasExitente.setTotalventa(ventasNuevo.getTotalventa());


        return ventasRepositorio.save(ventasExitente);
    }

    @Override
    public void delete(int id){
        if (ventasRepositorio.existsById(id)){
            ventasRepositorio.deleteById(id);
        }
    }
}
