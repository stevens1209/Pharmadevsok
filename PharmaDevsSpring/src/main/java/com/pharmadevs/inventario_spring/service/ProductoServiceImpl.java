package com.pharmadevs.inventario_spring.service;


import com.pharmadevs.inventario_spring.dao.ProductoRepositorio;
import com.pharmadevs.inventario_spring.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> findAll() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto findById(int id) {
        return productoRepositorio.findById(id).orElse(null);
    }

    @Override
    public Producto findOne(int id) {
        Optional<Producto> producto = productoRepositorio.findById(id);
        return producto.orElse(null);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public Producto update(int id, Producto productoNuevo) {
        Producto productoExistente = findOne(id);

        if (productoExistente == null) {
            return null;
        }

        productoExistente.setCategoria(productoNuevo.getCategoria());
        productoExistente.setDescripcion(productoNuevo.getDescripcion());
        productoExistente.setNombre(productoNuevo.getNombre());
        productoExistente.setPreciobase(productoNuevo.getPreciobase());
        productoExistente.setUnidadMedida(productoNuevo.getUnidadMedida());


        return productoRepositorio.save(productoExistente);
    }

    @Override
    public void delete(int id) {
        if (productoRepositorio.existsById(id)) {
            productoRepositorio.deleteById(id);
        }
    }
}