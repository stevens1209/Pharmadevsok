package com.distribuidanoc.test;

import com.distribuidanoc.entities.Bodega;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BodegaTest {

    private Bodega bodega;

    @BeforeEach
    public void setUp(){
        bodega = new Bodega(1,"Prospecto Blinker Plus",120,new Date());
    }

    @Test
    public void testBodegaConstructorAndGetter(){
        assertAll("Validar datos bodega",
                () -> assertEquals(1,bodega.getIdBodega()),
                () -> assertEquals("Prospecto Blinker Plus",bodega.getNombrematerial()),
                () -> assertEquals(120,bodega.getCantidad()),
                () -> assertEquals(new Date(),bodega.getFecharecepcion())
        );
    }

    @Test
    public void testBodegaSetters(){
        bodega = new Bodega();

        bodega.setIdBodega(2);
        bodega.setNombrematerial("Frascos de vidrio");
        bodega.setCantidad(100);
        bodega.setFecharecepcion(new Date());

        assertAll("Validar datos bodega",
                () -> assertEquals(2,bodega.getIdBodega()),
                () -> assertEquals("Frascos de vidrio",bodega.getNombrematerial()),
                () -> assertEquals(100,bodega.getCantidad()),
                () -> assertEquals(new Date(),bodega.getFecharecepcion())
                );
    }

    @Test
    public void TestToString() {
        String str = bodega.toString();
        assertAll("Validar datos bodega",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Prospecto Blinkler Plus")),
                () -> assertTrue(str.contains("120")),
                () -> assertTrue(str.contains((CharSequence) new Date()))
                );
    }
}
