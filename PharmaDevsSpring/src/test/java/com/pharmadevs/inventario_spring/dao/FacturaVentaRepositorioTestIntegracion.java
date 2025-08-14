package com.pharmadevs.inventario_spring.dao;


import com.pharmadevs.inventario_spring.model.Facturaventa;
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
public class FacturaVentaRepositorioTestIntegracion {
    @Autowired
    private FacturaVentaRepositorio facturaVentaRepositorio;

    @Test
    public void findAll(){
        List<Facturaventa> facturaventas = facturaVentaRepositorio.findAll();
        assertNotNull(facturaventas);
        assertTrue(facturaventas.size() > 0);

        for (Facturaventa facturaventa : facturaventas) {
            System.out.println(facturaventa.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Facturaventa> facturaventa = facturaVentaRepositorio.findById(1);
        assertTrue(facturaventa.isPresent(),"La factura venta con id=1 deberia existir");
        System.out.println(facturaventa.toString());
    }

    @Test
    public void save(){
        Facturaventa facturaventa = new Facturaventa(0,new Date(),"150.00");
        facturaVentaRepositorio.save(facturaventa);
        assertNotNull(facturaventa.getIdFacturaventa(),"La factura venta compra guaradado deberia tener un id");
        assertEquals("150",facturaventa.getMontototal());
    }

    @Test
    public void update() {
        Optional<Facturaventa> facturaventa = facturaVentaRepositorio.findById(1);

        assertTrue(facturaventa.isPresent(), "La factura venta con id=1 deberia existir para ser actualizado");

        facturaventa.orElse(null).setMontototal("150.00");
        //facturaventa.orElse(null).setFecha(new Date());

        Facturaventa facturaventaActualizado = facturaVentaRepositorio.save(facturaventa.orElse(null));
        assertEquals("150.00", facturaventaActualizado.getMontototal());
        assertEquals(new Date(), facturaventaActualizado.getFecha());
    }

    @Test
    public void delete () {
        if (facturaVentaRepositorio.existsById(1)) {
            facturaVentaRepositorio.deleteById(1);
        }
        assertFalse(facturaVentaRepositorio.existsById(1), "La factura venta con id=1 deberia haberse eliminado");
    }
}
