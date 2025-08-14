package com.distribuidanoc.test;

import com.distribuidanoc.entities.Proveedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProveedorTest {

    private Proveedor proveedor;

    @BeforeEach
    public void setUp(){
        proveedor = new Proveedor(1, "Imprenta Mariscal", "Pifo", "023941700", "example@example.com", "171234567001");
    }

    @Test
    public void testProveedorConstructorAndGetter(){
        assertAll("Validar datos proveedor",
                () -> assertEquals(1,proveedor.getIdProveedor()),
                () -> assertEquals("Imprenta Mariscal",proveedor.getNombre()),
                () -> assertEquals("Pifo",proveedor.getDireccion()),
                () -> assertEquals("023941700",proveedor.getTelefono()),
                () -> assertEquals("example@example.com",proveedor.getEmail()),
                () -> assertEquals("171234567001",proveedor.getRuc())
                );
    }

    @Test
    public void testProveedorSetter(){
        proveedor = new Proveedor();

        proveedor.setIdProveedor(2);
        proveedor.setNombre("Guimo");
        proveedor.setDireccion("Pelileo");
        proveedor.setTelefono("0984545455");
        proveedor.setEmail("example4@example.com");
        proveedor.setRuc("17171717001");

        assertAll("Validar datos proveedor",
                () -> assertEquals(2,proveedor.getIdProveedor()),
                () -> assertEquals("Guimo",proveedor.getNombre()),
                () -> assertEquals("Pelileo",proveedor.getDireccion()),
                () -> assertEquals("0984545455",proveedor.getTelefono()),
                () -> assertEquals("example4@example.com",proveedor.getEmail()),
                () -> assertEquals("17171717001",proveedor.getRuc())
        );
    }

    @Test
    public void TestToString(){
        String str = proveedor.toString();
        assertAll("Validar datos proveedor",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Imprenta Mariscal")),
                () -> assertTrue(str.contains("Pifo")),
                () -> assertTrue(str.contains("023941700")),
                () -> assertTrue(str.contains("example@example.com")),
                () -> assertTrue(str.contains("171234567001"))
                );

    }
}
