package com.pharmadevs.inventario_spring.dao;


import com.pharmadevs.inventario_spring.model.Bodega;
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
public class BodegaRepositorioTestIntegracion {
    @Autowired
    private BodegaRepositorio bodegaRepositorio;

    @Test
    public void findAll(){
        List<Bodega> bodegas = bodegaRepositorio.findAll();
        assertNotNull(bodegas);
        assertTrue(bodegas.size() > 0);

        for (Bodega bodega : bodegas) {
            System.out.println(bodega.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Bodega> bodega = bodegaRepositorio.findById(1);
        assertTrue(bodega.isPresent(),"La bodega con id=1 deberia existir");
        System.out.println(bodega.toString());
    }

    @Test
    public void save(){
        Bodega bodega = new Bodega(0,"Frasco",155,new Date());
        bodegaRepositorio.save(bodega);
        assertNotNull(bodega.getIdBodega(),"La bodega guaradada deberia tener un id");
        assertEquals(155,bodega.getCantidad());
    }

    @Test
    public void update(){
        Optional<Bodega> bodega = bodegaRepositorio.findById(1);

        assertTrue(bodega.isPresent(),"La bodega con id=1 deberia existir para ser actualizado");

        bodega.orElse(null).setNombrematerial("Pipetas");
        //bodega.orElse(null).setFecharecepcion(new Date();
        bodega.orElse(null).setCantidad(150);

        Bodega bodegaactualizado = bodegaRepositorio.save(bodega.orElse(null));
        assertEquals("Pipetas", bodegaactualizado.getNombrematerial());
       // assertEquals(new Date(), bodegaactualizado.getFecharecepcion());
        assertEquals(150, bodegaactualizado.getCantidad());
    }

    @Test
    public void delete(){
        if(bodegaRepositorio.existsById(1)){
            bodegaRepositorio.deleteById(1);
        }
        assertFalse(bodegaRepositorio.existsById(1),"La bodega con id=1 deberia haberse eliminado");
    }
}
