package com.pharmadevs.inventario_spring.dao;


import com.pharmadevs.inventario_spring.model.Ventas;
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
public class VentasRepositorioTestIntegracion {
    @Autowired
    private VentasRepositorio ventasRepositorio;

    @Test
    public void findAll(){
        List<Ventas> ventass = ventasRepositorio.findAll();
        assertNotNull(ventass);
        assertTrue(ventass.size() > 0);

        for (Ventas ventas : ventass) {
            System.out.println(ventas.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Ventas> ventas = ventasRepositorio.findById(1);
        assertTrue(ventas.isPresent(),"La venta con id=1 deberia existir");
        System.out.println(ventas.toString());
    }

    @Test
    public void save(){
        Ventas ventas = new Ventas(0,new Date(),"Efectivo","150.00");
        ventasRepositorio.save(ventas);
        assertNotNull(ventas.getIdVentas(),"La venta compra guaradado deberia tener un id");
        assertEquals("Efectivo",ventas.getFormapago());
        assertEquals("150.00",ventas.getTotalventa());
    }

    @Test
    public void update() {
        Optional<Ventas> ventas = ventasRepositorio.findById(1);

        assertTrue(ventas.isPresent(), "La venta con id=1 deberia existir para ser actualizado");

        ventas.orElse(null).setFormapago("Cheque");
        ventas.orElse(null).setTotalventa("180.00");

        Ventas ventasActualizado = ventasRepositorio.save(ventas.orElse(null));

        assertEquals("Cheque", ventasActualizado.getFormapago());
        assertEquals("180.00", ventasActualizado.getTotalventa());
    }

    @Test
    public void delete () {
        if (ventasRepositorio.existsById(1)) {
            ventasRepositorio.deleteById(1);
        }
        assertFalse(ventasRepositorio.existsById(1), "La venta con id=1 deberia haberse eliminado");
    }
}
