package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Almacen;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class AlmacenRepositorioTestIntegracion {
    @Autowired
    private AlmacenRepositorio almacenRepositorio;

    @Test
    public void findAll(){
        List<Almacen> almacens = almacenRepositorio.findAll();
        assertNotNull(almacens);
        assertTrue(almacens.size() > 0);

        for (Almacen almacen : almacens) {
            System.out.println(almacen.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Almacen> almacen = almacenRepositorio.findById(1);
        assertTrue(almacen.isPresent(),"El almacen con id=1 deberia existir");
        System.out.println(almacen.toString());
    }

    @Test
    public void save(){
        Almacen almacen = new Almacen(0,"12","Puembo");
        almacenRepositorio.save(almacen);
        assertNotNull(almacen.getIdAlmacen(),"El almacen guaradado deberia tener un id");
        assertEquals("Puembo",almacen.getUbicacion());
    }

    @Test
    public void update(){
        Optional<Almacen> almacen = almacenRepositorio.findById(1);

        assertTrue(almacen.isPresent(),"El almacen con id=1 deberia existir para ser actualizado");

        almacen.orElse(null).setUbicacion("Pifo");
        almacen.orElse(null).setStock("14");

        Almacen almacenActualizado = almacenRepositorio.save(almacen.orElse(null));
        assertEquals("Pifo", almacenActualizado.getUbicacion());
        assertEquals("14", almacenActualizado.getStock());
    }

    @Test
    public void delete(){
        if(almacenRepositorio.existsById(1)){
            almacenRepositorio.deleteById(1);
        }
        assertFalse(almacenRepositorio.existsById(1),"El almacen con id=1 deberia haberse eliminado");
    }
}
