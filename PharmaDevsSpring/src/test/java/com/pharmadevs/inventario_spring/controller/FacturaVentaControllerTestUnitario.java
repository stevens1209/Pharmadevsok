package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Facturaventa;
import com.pharmadevs.inventario_spring.service.FacturaVentaService;
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

public class FacturaVentaControllerTestUnitario {
    @InjectMocks
    private FacturaVentaController facturaVentaController;

    @Mock
    private FacturaVentaService facturaVentaService;

    private Facturaventa facturaventa;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        facturaventa = new Facturaventa();
        facturaventa.setIdFacturaventa(1);
        facturaventa.setFecha(new Date());
        facturaventa.setMontototal("150");
    }

    @Test
    public void testFindAll(){
        when(facturaVentaService.findAll()).thenReturn(List.of(facturaventa));
        ResponseEntity<List<Facturaventa>> respuesta = facturaVentaController.findALL();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(facturaVentaService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(facturaVentaService.findOne(1)).thenReturn(facturaventa);
        ResponseEntity<Facturaventa> respuesta = facturaVentaController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(facturaventa.getMontototal(),respuesta.getBody().getMontototal());
        verify(facturaVentaService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(facturaVentaService.findOne(2)).thenReturn(null);
        ResponseEntity<Facturaventa> respuesta = facturaVentaController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(facturaVentaService.save(any(Facturaventa.class))).thenReturn(facturaventa);
        ResponseEntity<Facturaventa> respuesta = facturaVentaController.save(facturaventa);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("150",respuesta.getBody().getMontototal());
    }

    @Test
    public void testUpdateExistente(){
        when(facturaVentaService.update(eq(1),any(Facturaventa.class))).thenReturn(facturaventa);
        ResponseEntity<Facturaventa> respuesta = facturaVentaController.update(1, facturaventa);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(facturaVentaService.update(eq(2),any(Facturaventa.class))).thenReturn(null);
        ResponseEntity<Facturaventa> respuesta = facturaVentaController.update(2, facturaventa);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(facturaVentaService).delete(1);
        ResponseEntity<Void> respuesta = facturaVentaController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(facturaVentaService,times(1)).delete(1);
    }
}
