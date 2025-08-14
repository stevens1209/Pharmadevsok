package com.distribuidanoc.test;

import com.distribuidanoc.entities.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente(1,"Soldis","Quito","0999999998","example1@example.com","171717171718001");
    }

    @Test
    public void testClienteConstructorAndGetter(){
        assertAll("Validardatos cliente",
                () -> assertEquals(1,cliente.getIdCliente()),
                () -> assertEquals("Soldis",cliente.getNombre()),
                () -> assertEquals("Quito",cliente.getDireccion()),
                () -> assertEquals("0999999998",cliente.getTelefono()),
                () -> assertEquals("example1@example.com",cliente.getEmail()),
                () -> assertEquals("171717171718001",cliente.getRuc())
                );
    }

    @Test
    public void testClienteSetters(){
        cliente = new Cliente();

        cliente.setIdCliente(2);
        cliente.setNombre("MaxiPets");
        cliente.setDireccion("Quito");
        cliente.setTelefono("099999982");
        cliente.setEmail("Example3@example.com");
        cliente.setRuc("17017141414001");

        assertAll("Validar datos de cliente",
                () -> assertEquals(2,cliente.getIdCliente()),
                () -> assertEquals("MaxiPets",cliente.getNombre()),
                () -> assertEquals("Quito",cliente.getDireccion()),
                () -> assertEquals("099999982",cliente.getTelefono()),
                () -> assertEquals("Example3@example.com",cliente.getEmail()),
                () -> assertEquals("17017141414001",cliente.getRuc())
                );
    }

    @Test
    public void TestToString(){
        String str = cliente.toString();
        assertAll("Validar datos cliente",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Soldis")),
                () -> assertTrue(str.contains("Quito")),
                () -> assertTrue(str.contains("0999999998")),
                () -> assertTrue(str.contains("example1@example.com")),
                () -> assertTrue(str.contains("171717171718001"))
                );
    }


}
