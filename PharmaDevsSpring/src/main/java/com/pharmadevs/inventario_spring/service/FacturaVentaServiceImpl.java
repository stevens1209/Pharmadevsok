package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.dao.FacturaVentaRepositorio;
import com.pharmadevs.inventario_spring.model.Facturaventa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaVentaServiceImpl implements FacturaVentaService{
    @Autowired
    FacturaVentaRepositorio facturaVentaRepositorio;

    @Override
    public List<Facturaventa> findAll(){
        return facturaVentaRepositorio.findAll();
    }

    @Override
    public Facturaventa findById(int id){
        return facturaVentaRepositorio.findById(id).orElse(null);
    }

    @Override
    public Facturaventa findOne(int id){
        Optional<Facturaventa> facturaventa = facturaVentaRepositorio.findById(id);
        return facturaventa.orElse(null);
    }

    @Override
    public Facturaventa save(Facturaventa facturaventa){
        return facturaVentaRepositorio.save(facturaventa);
    }

    @Override
    public Facturaventa update(int id, Facturaventa facturaventaNuevo){
        Facturaventa facturaventaExistente = findOne(id);

        if (facturaventaExistente == null){
            return null;
        }

        facturaventaExistente.setFecha(facturaventaNuevo.getFecha());
        facturaventaExistente.setMontototal(facturaventaNuevo.getMontototal());

        return facturaVentaRepositorio.save(facturaventaExistente);
    }

    @Override
    public void delete(int id){
        if (facturaVentaRepositorio.existsById(id)){
            facturaVentaRepositorio.deleteById(id);
        }
    }
}
