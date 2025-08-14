package com.distribuidanoc.test;

import com.distribuidanoc.entities.Ventas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class VentasTest {

    private Ventas ventas;

    @BeforeEach
    public void setUp(){
        ventas = new Ventas(1,new Date(),"Efectivo","126.385");
    }

    @Test
    public void testVentasConstrunctorAndGetter(){
        assertAll("Validar datos ventas",
                () -> assertEquals(1,ventas.getIdVentas()),
                () -> assertEquals(new Date(),ventas.getFechaventa()),
                () -> assertEquals("Efectivo",ventas.getFormapago()),
                () -> assertEquals("126.385",ventas.getTotalventa())
                );
    }

    @Test
    public void testVentasSetters(){
        ventas = new Ventas();

        ventas.setIdVentas(2);
        ventas.setFechaventa(new Date());
        ventas.setFormapago("Transferencia");
        ventas.setTotalventa("150.00");

        assertAll("Validar datos ventas",
                () -> assertEquals(2,ventas.getIdVentas()),
                () -> assertEquals(new Date(),ventas.getFechaventa()),
                () -> assertEquals("Transferencia",ventas.getFormapago()),
                () -> assertEquals("150.00",ventas.getTotalventa())
        );
    }

    @Test
    public void testToString(){
        String str = ventas.toString();

        assertAll("Validar datos ventas",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains((CharSequence) new Date())),
                () -> assertTrue(str.contains("Efectivo")),
                () -> assertTrue(str.contains("126.385"))
                );
    }
}
