package com.pharmadevs.inventario_spring.dao;


import com.pharmadevs.inventario_spring.model.Cliente;
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
public class ClienteRepositorioTestIntegracion {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    public void findAll(){
        List<Cliente> clientes = clienteRepositorio.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);

        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Cliente> cliente = clienteRepositorio.findById(1);
        assertTrue(cliente.isPresent(),"El cliente con id=1 deberia existir");
        System.out.println(cliente.toString());
    }

    @Test
    public void save(){
        Cliente cliente = new Cliente(0,"Pharmacys","Quito","023548484","Pharmacys@phaymacys.con.ec","18444444001");
        clienteRepositorio.save(cliente);
        assertNotNull(cliente.getIdCliente(),"El cliente guaradado deberia tener un id");
                assertEquals("Quito",cliente.getDireccion());
    }

    @Test
    public void update(){
        Optional<Cliente> cliente = clienteRepositorio.findById(1);

        assertTrue(cliente.isPresent(),"El cliente con id=1 deberia existir para ser actualizado");

        cliente.orElse(null).setTelefono("09888888");
        cliente.orElse(null).setEmail("example1@example");
        cliente.orElse(null).setDireccion("Pifo");
        cliente.orElse(null).setNombre("Cruz Azul");
        cliente.orElse(null).setRuc("1717171541001");

        Cliente clienteActualizado = clienteRepositorio.save(cliente.orElse(null));
        assertEquals("09888888", clienteActualizado.getTelefono());
        assertEquals("example1@example", clienteActualizado.getEmail());
        assertEquals("Pifo", clienteActualizado.getDireccion());
        assertEquals("Cruz Azul", clienteActualizado.getNombre());
        assertEquals("1717171541001", clienteActualizado.getRuc());
    }

    @Test
    public void delete(){
        if(clienteRepositorio.existsById(1)){
            clienteRepositorio.deleteById(1);
        }
        assertFalse(clienteRepositorio.existsById(1),"El cliente con id=1 deberia haberse eliminado");
    }
}
