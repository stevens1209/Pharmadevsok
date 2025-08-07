package com.pharmadevs.inventario_spring.service;


import com.pharmadevs.inventario_spring.dao.DetalleVentaRepositorio;
import com.pharmadevs.inventario_spring.model.Detallefacturaventa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService{
    @Autowired
    DetalleVentaRepositorio detalleVentaRepositorio;

    @Override
    public List<Detallefacturaventa> findAll(){
        return detalleVentaRepositorio.findAll();
    }

    @Override
    public Detallefacturaventa findById(int id){
        return detalleVentaRepositorio.findById(id).orElse(null);
    }

    @Override
    public Detallefacturaventa findOne(int id){
        Optional<Detallefacturaventa> detallefacturaventa = detalleVentaRepositorio.findById(id);
        return detallefacturaventa.orElse(null);
    }

    @Override
    public Detallefacturaventa save(Detallefacturaventa detallefacturaventa){
        return detalleVentaRepositorio.save(detallefacturaventa);
    }

    @Override
    public Detallefacturaventa update(int id, Detallefacturaventa detallefacturaventaNuevo){
        Detallefacturaventa detallefacturaventaExistente = findOne(id);

        if (detallefacturaventaExistente == null){
            return null;
        }

        detallefacturaventaExistente.setCantidad(detallefacturaventaNuevo.getCantidad());
        detallefacturaventaExistente.setIva(detallefacturaventaNuevo.getIva());
        detallefacturaventaExistente.setPreciounitario(detallefacturaventaNuevo.getPreciounitario());
        detallefacturaventaExistente.setSubtotal(detallefacturaventaNuevo.getSubtotal());


        return detalleVentaRepositorio.save(detallefacturaventaExistente);
    }

    @Override
    public void delete(int id){
        if (detalleVentaRepositorio.existsById(id)){
            detalleVentaRepositorio.deleteById(id);
        }
    }
}
