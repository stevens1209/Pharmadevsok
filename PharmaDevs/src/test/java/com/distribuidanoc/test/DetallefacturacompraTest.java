package com.distribuidanoc.test;

import com.distribuidanoc.entities.Detallefacturaventa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DetallefacturacompraTest {

    private Detallefacturaventa Detallefacturaventa;

    @BeforeEach
    public void setUp(){
        Detallefacturaventa = new Detallefacturaventa(1,"120",0.35F,"15%",42.00F);
    }

    @Test
    public void testDetallefacturaventaConstructorAndGetter(){
        assertAll("Validar datos detalle factura compra",
                () -> assertEquals(1,Detallefacturaventa.getIdDetallecompra()),
                () -> assertEquals("120",Detallefacturaventa.getCantidad()),
                () -> assertEquals(0.35F,Detallefacturaventa.getPreciounitario()),
                () -> assertEquals("15%",Detallefacturaventa.getIva()),
                () -> assertEquals(42.00F,Detallefacturaventa.getSubtotal())
                );
    }

    @Test
    public void testDetallefacturaventaSetters() {
        Detallefacturaventa = new Detallefacturaventa();

        Detallefacturaventa.setIdDetallecompra(2);
        Detallefacturaventa.setCantidad("100");
        Detallefacturaventa.setPreciounitario(0.40F);
        Detallefacturaventa.setIva("14%");
        Detallefacturaventa.setSubtotal(45.00F);

        assertAll("Validar datos detalle factura compra",
                () -> assertEquals(2,Detallefacturaventa.getIdDetallecompra()),
                () -> assertEquals("100",Detallefacturaventa.getCantidad()),
                () -> assertEquals(0.40F,Detallefacturaventa.getPreciounitario()),
                () -> assertEquals("14%",Detallefacturaventa.getIva()),
                () -> assertEquals(45.00F,Detallefacturaventa.getSubtotal())
                );
    }

    @Test
    public void TestToString(){
        String str = Detallefacturaventa.toString();
        assertAll("Validar datos detalle factura compra",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("120")),
                () -> assertTrue(str.contains("0.35F")),
                () -> assertTrue(str.contains("15%")),
                () -> assertTrue(str.contains("42.00F"))
                );
    }
}
