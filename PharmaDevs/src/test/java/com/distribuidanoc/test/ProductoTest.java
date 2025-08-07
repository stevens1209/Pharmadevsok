package com.distribuidanoc.test;

import com.distribuidanoc.entities.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    private Producto producto;

    @BeforeEach
    public void setUp(){
        producto = new Producto(1, "Blinker Plus", "Antiparasitario para perros, externo Tableta masticable", "Tabletas", "Unidades",10.99F);
    }

    @Test
    public void testPruductoConstructorAndGetter(){
        assertAll("Validar datos Producto",
                () -> assertEquals(1,producto.getIdProducto()),
                () -> assertEquals("Blinker Plus",producto.getNombre()),
                () -> assertEquals("Antiparasitario para perros, externo Tableta masticable",producto.getDescripcion()),
                () -> assertEquals("Tabletas",producto.getCategoria()),
                () -> assertEquals("Unidades",producto.getUnidadMedida()),
                () -> assertEquals(10.99F,producto.getPreciobase())
                );
    }

    @Test
    public void testProductoSetters(){
        producto = new Producto();

        producto.setIdProducto(2);
        producto.setNombre("Mascota");
        producto.setDescripcion("Vitaminas y Minerales");
        producto.setCategoria("Tabletas");
        producto.setUnidadMedida("Unidades");
        producto.setPreciobase(11.50F);

        assertAll("Validar datos Producto",
                () -> assertEquals(2,producto.getIdProducto()),
                () -> assertEquals("Mascota",producto.getNombre()),
                () -> assertEquals("Vitaminas y Minerales",producto.getDescripcion()),
                () -> assertEquals("Tabletas",producto.getCategoria()),
                () -> assertEquals("Unidades",producto.getUnidadMedida()),
                () -> assertEquals(11.50F,producto.getPreciobase())
        );
    }

    @Test
    public void TestToString(){
        String str = producto.toString();
        assertAll("Validar datos producto",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Blinker Plus")),
                () -> assertTrue(str.contains("Antiparasitario para perros, externo Tableta masticable")),
                () -> assertTrue(str.contains("Tabletas")),
                () -> assertTrue(str.contains("Unidades")),
                () -> assertTrue(str.contains("10.99F"))
                );
    }
}
