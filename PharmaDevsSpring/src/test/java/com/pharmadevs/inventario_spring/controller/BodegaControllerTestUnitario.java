package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Bodega;
import com.pharmadevs.inventario_spring.service.BodegaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BodegaControllerTestUnitario {
    @InjectMocks
    private BodegaController bodegaController;

    @Mock
    private BodegaService bodegaService;

    private Bodega bodega;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        bodega = new Bodega();
        bodega.setIdBodega(1);
        bodega.setFecharecepcion(new Date());
        bodega.setCantidad(150);
        bodega.setNombrematerial("Frascos");

    }

    @Test
    public void testFindAll(){
        when(bodegaService.findAll()).thenReturn(List.of(bodega));
        ResponseEntity<List<Bodega>> respuesta = bodegaController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(bodegaService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(bodegaService.findOne(1)).thenReturn(bodega);
        ResponseEntity<Bodega> respuesta = bodegaController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(bodega.getCantidad(),respuesta.getBody().getCantidad());
        verify(bodegaService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(bodegaService.findOne(2)).thenReturn(null);
        ResponseEntity<Bodega> respuesta = bodegaController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(bodegaService.save(any(Bodega.class))).thenReturn(bodega);
        ResponseEntity<Bodega> respuesta = bodegaController.save(bodega);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(150,respuesta.getBody().getCantidad());
    }

    @Test
    public void testUpdateExistente(){
        when(bodegaService.update(eq(1),any(Bodega.class))).thenReturn(bodega);
        ResponseEntity<Bodega> respuesta = bodegaController.update(1, bodega);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(bodegaService.update(eq(2),any(Bodega.class))).thenReturn(null);
        ResponseEntity<Bodega> respuesta = bodegaController.update(2, bodega);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(bodegaService).delete(1);
        ResponseEntity<Void> respuesta = bodegaController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(bodegaService,times(1)).delete(1);
    }
}
