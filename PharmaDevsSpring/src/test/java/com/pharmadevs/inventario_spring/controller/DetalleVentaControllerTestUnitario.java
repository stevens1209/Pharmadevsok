package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Detallefacturaventa;
import com.pharmadevs.inventario_spring.service.DetalleVentaService;
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

public class DetalleVentaControllerTestUnitario {
    @InjectMocks
    private DetalleVentaController detalleVentaController;

    @Mock
    private DetalleVentaService detalleVentaService;

    private Detallefacturaventa detallefacturaventa;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        detallefacturaventa = new Detallefacturaventa();
        detallefacturaventa.setIdDetalleventa(1);
        detallefacturaventa.setIva("Ecuador");
        detallefacturaventa.setSubtotal(150F);
        detallefacturaventa.setCantidad(150);
        detallefacturaventa.setPreciounitario(19);
    }

    @Test
    public void testFindAll(){
        when(detalleVentaService.findAll()).thenReturn(List.of(detallefacturaventa));
        ResponseEntity<List<Detallefacturaventa>> respuesta = detalleVentaController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(detalleVentaService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(detalleVentaService.findOne(1)).thenReturn(detallefacturaventa);
        ResponseEntity<Detallefacturaventa> respuesta = detalleVentaController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(detallefacturaventa.getCantidad(),respuesta.getBody().getCantidad());
        verify(detalleVentaService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(detalleVentaService.findOne(2)).thenReturn(null);
        ResponseEntity<Detallefacturaventa> respuesta = detalleVentaController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(detalleVentaService.save(any(Detallefacturaventa.class))).thenReturn(detallefacturaventa);
        ResponseEntity<Detallefacturaventa> respuesta = detalleVentaController.save(detallefacturaventa);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(150,respuesta.getBody().getCantidad());
    }

    @Test
    public void testUpdateExistente(){
        when(detalleVentaService.update(eq(1),any(Detallefacturaventa.class))).thenReturn(detallefacturaventa);
        ResponseEntity<Detallefacturaventa> respuesta = detalleVentaController.update(1, detallefacturaventa);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(detalleVentaService.update(eq(2),any(Detallefacturaventa.class))).thenReturn(null);
        ResponseEntity<Detallefacturaventa> respuesta = detalleVentaController.update(2, detallefacturaventa);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(detalleVentaService).delete(1);
        ResponseEntity<Void> respuesta = detalleVentaController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(detalleVentaService,times(1)).delete(1);
    }
}
