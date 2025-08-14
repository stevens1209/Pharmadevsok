package com.distribuidanoc.test;

import com.distribuidanoc.entities.Detallefacturaventa;
import com.distribuidanoc.entities.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DetallefacturaventaTest {

    private Detallefacturaventa detallefacturaventa;

     @BeforeEach
    public void setUp(){
         detallefacturaventa = new Detallefacturaventa(1,10,10.99F,"15%",109.9F);
     }

     @Test
    public void testDetallefacturaventaConstructorAndGetter(){
         assertAll("Validar datos detallefacturaventa",
                 () -> assertEquals(1,detallefacturaventa.getIdDetalleventa()),
                 () -> assertEquals(10,detallefacturaventa.getCantidad()),
                 () -> assertEquals(10.99F,detallefacturaventa.getPreciounitario()),
                 () -> assertEquals("15%",detallefacturaventa.getIva()),
                 () -> assertEquals(109.9F,detallefacturaventa.getSubtotal())
                 );
     }

     @Test
    public void testDetallefacturaventaSetters(){
         detallefacturaventa = new Detallefacturaventa();

         detallefacturaventa.setIdDetalleventa(2);
         detallefacturaventa.setCantidad(20);
         detallefacturaventa.setPreciounitario(10.00F);
         detallefacturaventa.setIva("14%");
         detallefacturaventa.setSubtotal(100.00F);

         assertAll("Validar datos facturadetalleventa",
                 () -> assertEquals(2,detallefacturaventa.getIdDetalleventa()),
                 () -> assertEquals(20,detallefacturaventa.getCantidad()),
                 () -> assertEquals(10.00F,detallefacturaventa.getPreciounitario()),
                 () -> assertEquals("14%",detallefacturaventa.getIva()),
                 () -> assertEquals(100.00F,detallefacturaventa.getSubtotal())
                 );
     }

     @Test
    public void TestToString(){
         String str = detallefacturaventa.toString();
         assertAll("Validar datos facturadetalleventa",
                 () -> assertTrue(str.contains("1")),
                 () -> assertTrue(str.contains("10")),
                 () -> assertTrue(str.contains("10.99F")),
                 () -> assertTrue(str.contains("15%")),
                 () -> assertTrue(str.contains("109.9F"))
                 );
     }
}
