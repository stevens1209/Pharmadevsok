package com.pharmadevs.inventario_spring.dao;


import com.pharmadevs.inventario_spring.model.Facturacompra;
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
public class FacturaCompraRepositorioTestIntegracion {
    @Autowired
    private FacturaCompraRepositorio facturaCompraRepositorio;

    @Test
    public void findAll(){
        List<Facturacompra> facturacompras = facturaCompraRepositorio.findAll();
        assertNotNull(facturacompras);
        assertTrue(facturacompras.size() > 0);

        for (Facturacompra facturacompra : facturacompras) {
            System.out.println(facturacompra.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Facturacompra> facturacompra = facturaCompraRepositorio.findById(1);
        assertTrue(facturacompra.isPresent(),"La factura compra con id=1 deberia existir");
        System.out.println(facturacompra.toString());
    }

    @Test
    public void save(){
        Facturacompra facturacompra = new Facturacompra(0,new Date(),"150.00");
        facturaCompraRepositorio.save(facturacompra);
        assertNotNull(facturacompra.getIdFacturacompra(),"La factura compra guaradado deberia tener un id");
        assertEquals("150",facturacompra.getMontototal());
    }

    @Test
    public void update() {
        Optional<Facturacompra> facturacompra = facturaCompraRepositorio.findById(1);

        assertTrue(facturacompra.isPresent(), "La factura compra con id=1 deberia existir para ser actualizado");

        facturacompra.orElse(null).setMontototal("150.00");
        //facturacompra.orElse(null).setFecha(new Date());

        Facturacompra facturacompraActualizado = facturaCompraRepositorio.save(facturacompra.orElse(null));
        assertEquals("150.00", facturacompraActualizado.getMontototal());
        assertEquals(new Date(), facturacompraActualizado.getFecha());
    }

    @Test
    public void delete () {
        if (facturaCompraRepositorio.existsById(1)) {
            facturaCompraRepositorio.deleteById(1);
        }
        assertFalse(facturaCompraRepositorio.existsById(1), "La factura compra con id=1 deberia haberse eliminado");
    }
}