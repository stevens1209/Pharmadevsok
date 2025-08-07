package com.pharmadevs.inventario_spring.dao;


import com.pharmadevs.inventario_spring.model.Detallefacturacompra;
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
public class DetalleCompraRepositorioTestIntegracion {
    @Autowired
    private DetalleCompraRepositorio detalleCompraRepositorio;

    @Test
    public void findAll(){
        List<Detallefacturacompra> detallefacturacompras = detalleCompraRepositorio.findAll();
        assertNotNull(detallefacturacompras);
        assertTrue(detallefacturacompras.size() > 0);

        for (Detallefacturacompra detallefacturacompra : detallefacturacompras) {
            System.out.println(detallefacturacompra.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Detallefacturacompra> detallefacturacompra = detalleCompraRepositorio.findById(1);
        assertTrue(detallefacturacompra.isPresent(),"El detalle de factura compra con id=1 deberia existir");
        System.out.println(detallefacturacompra.toString());
    }

    @Test
    public void save(){
        Detallefacturacompra detallefacturacompra = new Detallefacturacompra(0,"150",150.00F,"15%",156.00F);
        detalleCompraRepositorio.save(detallefacturacompra);
        assertNotNull(detallefacturacompra.getIdDetallecompra(),"El detalle de factura compra guaradado deberia tener un id");
        assertEquals("150",detallefacturacompra.getCantidad());
    }

    @Test
    public void update(){
        Optional<Detallefacturacompra> detallefacturacompra = detalleCompraRepositorio.findById(1);

        assertTrue(detallefacturacompra.isPresent(),"El detalle de factura compra con id=1 deberia existir para ser actualizado");

        detallefacturacompra.orElse(null).setSubtotal(120.15F);
        detallefacturacompra.orElse(null).setIva("15%");
        detallefacturacompra.orElse(null).setPreciounitario(10.00F);
        detallefacturacompra.orElse(null).setCantidad("10");


        Detallefacturacompra detallefacturacompraActualizado = detalleCompraRepositorio.save(detallefacturacompra.orElse(null));
        assertEquals(120.15F, detallefacturacompraActualizado.getSubtotal());
        assertEquals("15% Azul", detallefacturacompraActualizado.getIva());
        assertEquals(10.00F, detallefacturacompraActualizado.getPreciounitario());
        assertEquals("10", detallefacturacompraActualizado.getCantidad());
    }

    @Test
    public void delete(){
        if(detalleCompraRepositorio.existsById(1)){
            detalleCompraRepositorio.deleteById(1);
        }
        assertFalse(detalleCompraRepositorio.existsById(1),"El detalle de factura compra con id=1 deberia haberse eliminado");
    }
}