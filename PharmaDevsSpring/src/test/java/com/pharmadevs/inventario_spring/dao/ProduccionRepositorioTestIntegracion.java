package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Produccion;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ProduccionRepositorioTestIntegracion {
    @Autowired
    private ProduccionRepositorio produccionRepositorio;

    @Test
    public void findAll(){
        List<Produccion> produccions = produccionRepositorio.findAll();
        assertNotNull(produccions);
        assertTrue(produccions.size() > 0);

        for (Produccion produccion : produccions) {
            System.out.println(produccion.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Produccion> produccion = produccionRepositorio.findById(1);
        assertTrue(produccion.isPresent(),"La produccion con id=1 deberia existir");
        System.out.println(produccion.toString());
    }

    @Test
    public void save(){
        Produccion produccion = new Produccion(0,new Date(),new Date(),"V2506001","150 unidades");
        produccionRepositorio.save(produccion);
        assertNotNull(produccion.getIdProduccion(),"La produccion compra guaradado deberia tener un id");
        assertEquals(new Date(),produccion.getFechaproduccion());
        assertEquals(new Date(),produccion.getFechavencimiento());
        assertEquals("V2506001",produccion.getLote());
        assertEquals("150 unidades",produccion.getCantidadproducida());
    }

    @Test
    public void update() {
        Optional<Produccion> produccion = produccionRepositorio.findById(1);

        assertTrue(produccion.isPresent(), "La produccion con id=1 deberia existir para ser actualizado");

        produccion.orElse(null).setFechaproduccion(new Date());
        produccion.orElse(null).setFechavencimiento(new Date());
        produccion.orElse(null).setLote("V2506002");
        produccion.orElse(null).setCantidadproducida("1500 unidades ");


        Produccion produccionActualizado = produccionRepositorio.save(produccion.orElse(null));

        assertEquals(new Date(), produccionActualizado.getFechaproduccion());
        assertEquals(new Date(), produccionActualizado.getFechavencimiento());
        assertEquals("V2506002", produccionActualizado.getLote());
        assertEquals("1500 unidades", produccionActualizado.getCantidadproducida());

    }

    @Test
    public void delete () {
        if (produccionRepositorio.existsById(1)) {
            produccionRepositorio.deleteById(1);
        }
        assertFalse(produccionRepositorio.existsById(1), "La produccion con id=1 deberia haberse eliminado");
    }
}
