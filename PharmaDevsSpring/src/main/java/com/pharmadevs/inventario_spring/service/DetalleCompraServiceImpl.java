package com.pharmadevs.inventario_spring.service;


import com.pharmadevs.inventario_spring.dao.DetalleCompraRepositorio;
import com.pharmadevs.inventario_spring.model.Detallefacturacompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCompraServiceImpl implements DetalleCompraService{
    @Autowired
    DetalleCompraRepositorio detalleCompraRepositorio;

    @Override
    public List<Detallefacturacompra> findAll(){
        return detalleCompraRepositorio.findAll();
    }

    @Override
    public Detallefacturacompra findById(int id){
        return detalleCompraRepositorio.findById(id).orElse(null);
    }

    @Override
    public Detallefacturacompra findOne(int id){
        Optional<Detallefacturacompra> detallefacturacompra = detalleCompraRepositorio.findById(id);
        return detallefacturacompra.orElse(null);
    }

    @Override
    public Detallefacturacompra save(Detallefacturacompra detallefacturacompra){
        return detalleCompraRepositorio.save(detallefacturacompra);
    }

    @Override
    public Detallefacturacompra update(int id, Detallefacturacompra detallefacturacompraNuevo){
        Detallefacturacompra detallefacturacompraExistente = findOne(id);

        if (detallefacturacompraExistente == null){
            return null;
        }

        detallefacturacompraExistente.setCantidad(detallefacturacompraNuevo.getCantidad());
        detallefacturacompraExistente.setIva(detallefacturacompraNuevo.getIva());
        detallefacturacompraExistente.setPreciounitario(detallefacturacompraNuevo.getPreciounitario());
        detallefacturacompraExistente.setSubtotal(detallefacturacompraNuevo.getSubtotal());


        return detalleCompraRepositorio.save(detallefacturacompraExistente);
    }

    @Override
    public void delete(int id){
        if (detalleCompraRepositorio.existsById(id)){
            detalleCompraRepositorio.deleteById(id);
        }
    }
}