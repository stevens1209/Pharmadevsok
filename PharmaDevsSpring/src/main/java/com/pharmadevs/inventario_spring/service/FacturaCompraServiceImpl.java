package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.dao.FacturaCompraRepositorio;
import com.pharmadevs.inventario_spring.model.Facturacompra;
import com.pharmadevs.inventario_spring.model.Facturaventa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FacturaCompraServiceImpl implements FacturaCompraService{
    @Autowired
    FacturaCompraRepositorio facturaCompraRepositorio;

    @Override
    public List<Facturacompra> findAll(){
        return facturaCompraRepositorio.findAll();
    }

    @Override
    public Facturacompra findById(int id){
        return facturaCompraRepositorio.findById(id).orElse(null);
    }

    @Override
    public Facturacompra findOne(int id){
        Optional<Facturacompra> facturacompra = facturaCompraRepositorio.findById(id);
        return facturacompra.orElse(null);
    }

    @Override
    public Facturacompra save(Facturacompra facturacompra){
        return facturaCompraRepositorio.save(facturacompra);
    }

    @Override
    public Facturacompra update(int id, Facturacompra facturacompraNuevo){
        Facturacompra facturacompraExistente = findOne(id);

        if (facturacompraExistente == null){
            return null;
        }

        facturacompraExistente.setFecha(facturacompraNuevo.getFecha());
        facturacompraExistente.setMontototal(facturacompraNuevo.getMontototal());

        return facturaCompraRepositorio.save(facturacompraExistente);
    }

    @Override
    public void delete(int id){
        if (facturaCompraRepositorio.existsById(id)){
            facturaCompraRepositorio.deleteById(id);
        }
    }
}