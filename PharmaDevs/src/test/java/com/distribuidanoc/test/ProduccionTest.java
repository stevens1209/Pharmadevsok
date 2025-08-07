package com.distribuidanoc.test;

import com.distribuidanoc.entities.Produccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ProduccionTest {
    private Produccion produccion;
    @BeforeEach
    public  void setUp(){
        Produccion produccion = new Produccion(1,new Date(),new Date(),"0001","1000");
    }

    @Test
    public void testProduccionConstructorAndGetter(){
        assertAll("Validar datos de produccion",
                () -> assertEquals(1,produccion.getIdProduccion()),
                () -> assertEquals(new Date(),produccion.getFechaproduccion()),
                () -> assertEquals(new Date(),produccion.getFechavencimiento()),
                () -> assertEquals("0001",produccion.getLote()),
                () -> assertEquals("1000",produccion.getCantidadproducida())
                );
    }

    @Test
    public void testProduccionSetters(){
        produccion = new Produccion();

        produccion.setIdProduccion(2);
        produccion.setFechaproduccion(new Date());
        produccion.setFechavencimiento(new Date());
        produccion.setLote("0002");
        produccion.setCantidadproducida("1110");

        assertAll("Validar datos de produccion",
                () -> assertEquals(2,produccion.getIdProduccion()),
                () -> assertEquals(new Date(),produccion.getFechaproduccion()),
                () -> assertEquals(new Date(),produccion.getFechavencimiento()),
                () -> assertEquals("0002",produccion.getLote()),
                () -> assertEquals("1110",produccion.getCantidadproducida())
        );
    }

    @Test
    public void TestToString(){
        String str = produccion.toString();
        assertAll("Validar datos de produccion",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains((CharSequence) new Date())),
                () -> assertTrue(str.contains((CharSequence) new Date())),
                () -> assertTrue(str.contains("0001")),
                () -> assertTrue(str.contains("1000"))
                );

    }
}
