package com.pharmadevs.inventario_spring.service;

import com.pharmadevs.inventario_spring.model.Produccion;

import java.util.List;

public interface ProduccionService {
    public List<Produccion> findAll();

    Produccion findById(int id);

    public Produccion findOne(int id);
    public Produccion save(Produccion produccion);
   public  Produccion update(int id, Produccion produccion);
    public void delete(int id);
}
