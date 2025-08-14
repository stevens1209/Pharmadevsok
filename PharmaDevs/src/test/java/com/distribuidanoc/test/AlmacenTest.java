package com.distribuidanoc.test;

import com.distribuidanoc.entities.Almacen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class AlmacenTest {

    private Almacen almacen;

    @BeforeEach
    public void setUp(){
        almacen = new Almacen(1,"1000","Puembo");
    }

    @Test
    public void testAlmacenCondtructorAndGetter(){
        assertAll("Validar datos Almacen",
                () -> assertEquals(1,almacen.getIdAlmacen()),
                () -> assertEquals("1000",almacen.getStock()),
                () -> assertEquals("Puembo",almacen.getUbicacion())
                );
    }

    @Test
    public void testAlmacenSetters(){
        almacen = new Almacen();

        almacen.setIdAlmacen(2);
        almacen.setStock("10");
        almacen.setUbicacion("Pifo");

        assertAll("Validar datos Almacen",
                () -> assertEquals(2,almacen.getIdAlmacen()),
                () -> assertEquals("10",almacen.getStock()),
                () -> assertEquals("Pifo",almacen.getUbicacion())
                );
    }

    @Test
    public void TestToString(){
        String str = almacen.toString();
        assertAll("Validar datos de almacen",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1000")),
                () -> assertTrue(str.contains("Puembo"))
                );
    }
}
