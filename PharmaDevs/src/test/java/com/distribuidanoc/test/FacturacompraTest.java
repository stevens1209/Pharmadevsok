package com.distribuidanoc.test;

import com.distribuidanoc.entities.Facturaventa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturacompraTest {

    private Fac Facturaventa;

    @BeforeEach
    public void setUp(){
        Facturaventa = new Facturaventa(1,new Date(),"48.3");
    }

    @Test
    public void testFacturaventaConstructorAndGetter(){
        assertAll("Validar datos Facturaventa",
                () -> assertEquals(1,Facturaventa.getIdFacturaventa()),
                () -> assertEquals(new Date(),Facturaventa.getFecha()),
                () -> assertEquals("48.3",Facturaventa.getMontototal())
                );
    }
    @Test
    public void testFacturaventaSetters(){
        Facturaventa = new Facturaventa();

        Facturaventa.setIdFacturaventa(2);
        Facturaventa.setFecha(new Date());
        Facturaventa.setMontototal("50.00");

        assertAll("Validar datos Facturaventa",
                () -> assertEquals(2,Facturaventa.getIdFacturaventa()),
                () -> assertEquals(new Date(),Facturaventa.getFecha()),
                () -> assertEquals("50.00",Facturaventa.getMontototal())
                );
    }

    @Test
    public void TestToString(){
        String str = Facturaventa.toString();
        assertAll("Validar fatos Facturaventa",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains((CharSequence) new Date())),
                () -> assertTrue(str.contains("48.3"))
                );
    }
}
