package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Proveedor;
import com.pharmadevs.inventario_spring.service.ProveedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProveedorControllerTestUnitario {
    @InjectMocks
    private ProveedorController proveedorController;

    @Mock
    private ProveedorService proveedorService;

    private Proveedor proveedor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        proveedor.setNombre("Guimo");
        proveedor.setDireccion("Quito");
        proveedor.setTelefono("0988329455");
        proveedor.setEmail("Guimo@gmail.com");
        proveedor.setRuc("1754548484001");
    }

    @Test
    public void testFindAll(){
        when(proveedorService.findAll()).thenReturn(List.of(proveedor));
        ResponseEntity<List<Proveedor>> respuesta = proveedorController.findALL();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(proveedorService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(proveedorService.findOne(1)).thenReturn(proveedor);
        ResponseEntity<Proveedor> respuesta = proveedorController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(proveedor.getNombre(),respuesta.getBody().getNombre());
        verify(proveedorService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(proveedorService.findOne(2)).thenReturn(null);
        ResponseEntity<Proveedor> respuesta = proveedorController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(proveedorService.save(any(Proveedor.class))).thenReturn(proveedor);
        ResponseEntity<Proveedor> respuesta = proveedorController.save(proveedor);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Guimo",respuesta.getBody().getNombre());
    }

    @Test
    public void testUpdateExistente(){
        when(proveedorService.update(eq(1),any(Proveedor.class))).thenReturn(proveedor);
        ResponseEntity<Proveedor> respuesta = proveedorController.update(1, proveedor);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(proveedorService.update(eq(2),any(Proveedor.class))).thenReturn(null);
        ResponseEntity<Proveedor> respuesta = proveedorController.update(2, proveedor);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(proveedorService).delete(1);
        ResponseEntity<Void> respuesta = proveedorController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(proveedorService,times(1)).delete(1);
    }
}
