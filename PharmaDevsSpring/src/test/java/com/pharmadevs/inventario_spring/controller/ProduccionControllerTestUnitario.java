package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Produccion;
import com.pharmadevs.inventario_spring.service.ProduccionService;
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

public class ProduccionControllerTestUnitario {
    @InjectMocks
    private ProduccionController produccionController;

    @Mock
    private ProduccionService produccionService;

    private Produccion produccion;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        produccion = new Produccion();
        produccion.setIdProduccion(1);
        produccion.setCantidadproducida("150");
        produccion.setFechaproduccion(new Date());
        produccion.setFechavencimiento(new Date());
        produccion.setLote("A2507001");
        }

    @Test
    public void testFindAll(){
        when(produccionService.findAll()).thenReturn(List.of(produccion));
        ResponseEntity<List<Produccion>> respuesta = produccionController.findALL();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(produccionService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(produccionService.findOne(1)).thenReturn(produccion);
        ResponseEntity<Produccion> respuesta = produccionController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(produccion.getLote(),respuesta.getBody().getLote());
        verify(produccionService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(produccionService.findOne(2)).thenReturn(null);
        ResponseEntity<Produccion> respuesta = produccionController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(produccionService.save(any(Produccion.class))).thenReturn(produccion);
        ResponseEntity<Produccion> respuesta = produccionController.save(produccion);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("150",respuesta.getBody().getCantidadproducida());
    }

    @Test
    public void testUpdateExistente(){
        when(produccionService.update(eq(1),any(Produccion.class))).thenReturn(produccion);
        ResponseEntity<Produccion> respuesta = produccionController.update(1, produccion);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(produccionService.update(eq(2),any(Produccion.class))).thenReturn(null);
        ResponseEntity<Produccion> respuesta = produccionController.update(2, produccion);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(produccionService).delete(1);
        ResponseEntity<Void> respuesta = produccionController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(produccionService,times(1)).delete(1);
    }
}
