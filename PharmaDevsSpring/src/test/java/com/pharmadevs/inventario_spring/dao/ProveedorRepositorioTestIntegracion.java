package com.pharmadevs.inventario_spring.dao;


import com.pharmadevs.inventario_spring.model.Proveedor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ProveedorRepositorioTestIntegracion {
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Test
    public void findAll(){
        List<Proveedor> proveedors = proveedorRepositorio.findAll();
        assertNotNull(proveedors);
        assertTrue(proveedors.size() > 0);

        for (Proveedor proveedor : proveedors) {
            System.out.println(proveedor.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Proveedor> proveedor = proveedorRepositorio.findById(1);
        assertTrue(proveedor.isPresent(),"El proveedor con id=1 deberia existir");
        System.out.println(proveedor.toString());
    }

    @Test
    public void save(){
        Proveedor proveedor = new Proveedor(0,"Guimo","Latacunga","025547854","guimo@guimo.ec","175188441001");
        proveedorRepositorio.save(proveedor);
        assertNotNull(proveedor.getIdProveedor(),"El proveedor  guaradado deberia tener un id");
        assertEquals("Guimo",proveedor.getNombre());
        assertEquals("Latacunga",proveedor.getDireccion());
        assertEquals("025547854",proveedor.getTelefono());
        assertEquals("guimo@guimo.ec",proveedor.getEmail());
        assertEquals("175188441001",proveedor.getRuc());
    }

    @Test
    public void update() {
        Optional<Proveedor> proveedor = proveedorRepositorio.findById(1);

        assertTrue(proveedor.isPresent(), " El proveedor con id=1 deberia existir para ser actualizado");

        proveedor.orElse(null).setNombre("Imprenta Mariscal");
        proveedor.orElse(null).setDireccion("Pifo");
        proveedor.orElse(null).setTelefono("098787878");
        proveedor.orElse(null).setEmail("Lamarical@lamarical.ec");
        proveedor.orElse(null).setRuc("178798784001");


        Proveedor proveedorActualizado = proveedorRepositorio.save(proveedor.orElse(null));

        assertEquals("Imprenta Mariscal", proveedorActualizado.getNombre());
        assertEquals("Pifo", proveedorActualizado.getDireccion());
        assertEquals("098787878", proveedorActualizado.getTelefono());
        assertEquals("Lamarical@lamarical.ec", proveedorActualizado.getEmail());
        assertEquals("178798784001", proveedorActualizado.getRuc());

    }

    @Test
    public void delete () {
        if (proveedorRepositorio.existsById(1)) {
            proveedorRepositorio.deleteById(1);
        }
        assertFalse(proveedorRepositorio.existsById(1), "El proveedor venta con id=1 deberia haberse eliminado");
    }
}
