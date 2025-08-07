package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Cliente;
import com.pharmadevs.inventario_spring.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cglib.core.ClassesKey;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ClienteControllerTestUnitario {
    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setEmail("Ecuador");
        cliente.setNombre("Jose");
        cliente.setRuc("Hernandez");
        cliente.setDireccion("Conocoto");
        cliente.setTelefono("0988329455");
    }

    @Test
    public void testFindAll(){
        when(clienteService.findAll()).thenReturn(List.of(cliente));
        ResponseEntity<List<Cliente>> respuesta = clienteController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(clienteService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(clienteService.findOne(1)).thenReturn(cliente);
        ResponseEntity<Cliente> respuesta = clienteController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(cliente.getNombre(),respuesta.getBody().getNombre());
        verify(clienteService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(clienteService.findOne(2)).thenReturn(null);
        ResponseEntity<Cliente> respuesta = clienteController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);
        ResponseEntity<Cliente> respuesta = clienteController.save(cliente);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Jose",respuesta.getBody().getNombre());
    }

    @Test
    public void testUpdateExistente(){
        when(clienteService.update(eq(1),any(Cliente.class))).thenReturn(cliente);
        ResponseEntity<Cliente> respuesta = clienteController.update(1, cliente);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(clienteService.update(eq(2),any(Cliente.class))).thenReturn(null);
        ResponseEntity<Cliente> respuesta = clienteController.update(2, cliente);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(clienteService).delete(1);
        ResponseEntity<Void> respuesta = clienteController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(clienteService,times(1)).delete(1);
    }
}
