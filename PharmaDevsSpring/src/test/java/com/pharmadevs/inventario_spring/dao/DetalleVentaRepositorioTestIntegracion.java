package com.pharmadevs.inventario_spring.dao;

import com.pharmadevs.inventario_spring.model.Detallefacturaventa;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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
public class DetalleVentaRepositorioTestIntegracion {
    @Autowired
    private DetalleVentaRepositorio detalleVentaRepositorio;

    @Test
    public void findAll(){
        List<Detallefacturaventa> detallefacturaventas = detalleVentaRepositorio.findAll();
        assertNotNull(detallefacturaventas);
        assertTrue(detallefacturaventas.size() > 0);

        for (Detallefacturaventa detallefacturaventa : detallefacturaventas) {
            System.out.println(detallefacturaventa.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Detallefacturaventa> detallefacturaventa = detalleVentaRepositorio.findById(1);
        assertTrue(detallefacturaventa.isPresent(),"El detalle de factura venta con id=1 deberia existir");
        System.out.println(detallefacturaventa.toString());
    }

    @Test
    public void save(){
        Detallefacturaventa detallefacturaventa = new Detallefacturaventa(0,150,150.00F,"15%",156.00F);
        detalleVentaRepositorio.save(detallefacturaventa);
        assertNotNull(detallefacturaventa.getIdDetalleventa(),"El detalle de factura venta guaradado deberia tener un id");
        assertEquals("150",detallefacturaventa.getCantidad());
    }

    @Test
    public void update(){
        Optional<Detallefacturaventa> detallefacturaventa = detalleVentaRepositorio.findById(1);

        assertTrue(detallefacturaventa.isPresent(),"El detalle de factura venta con id=1 deberia existir para ser actualizado");

        detallefacturaventa.orElse(null).setSubtotal(120.15F);
        detallefacturaventa.orElse(null).setIva("15%");
        detallefacturaventa.orElse(null).setPreciounitario(10.00F);
        detallefacturaventa.orElse(null).setCantidad(10);


        Detallefacturaventa detallefacturaventaActualizado = detalleVentaRepositorio.save(detallefacturaventa.orElse(null));
        assertEquals(120.15F, detallefacturaventaActualizado.getSubtotal());
        assertEquals("15% Azul", detallefacturaventaActualizado.getIva());
        assertEquals(10.00F, detallefacturaventaActualizado.getPreciounitario());
        assertEquals(10, detallefacturaventaActualizado.getCantidad());
    }

    @Test
    public void delete(){
        if(detalleVentaRepositorio.existsById(1)){
            detalleVentaRepositorio.deleteById(1);
        }
        assertFalse(detalleVentaRepositorio.existsById(1),"El detalle de factura venta con id=1 deberia haberse eliminado");
    }
}
