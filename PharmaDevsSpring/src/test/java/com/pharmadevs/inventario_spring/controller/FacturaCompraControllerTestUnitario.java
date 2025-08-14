package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Facturacompra;
import com.pharmadevs.inventario_spring.service.FacturaCompraService;
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

public class FacturaCompraControllerTestUnitario {
    @InjectMocks
    private FacturaCompraController facturaCompraController;

    @Mock
    private FacturaCompraService facturaCompraService;

    private Facturacompra facturacompra;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        facturacompra = new Facturacompra();
        facturacompra.setIdFacturacompra(1);
        facturacompra.setFecha(new Date());
        facturacompra.setMontototal("150");
    }

    @Test
    public void testFindAll(){
        when(facturaCompraService.findAll()).thenReturn(List.of(facturacompra));
        ResponseEntity<List<Facturacompra>> respuesta = facturaCompraController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(facturaCompraService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(facturaCompraService.findOne(1)).thenReturn(facturacompra);
        ResponseEntity<Facturacompra> respuesta = facturaCompraController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(facturacompra.getMontototal(),respuesta.getBody().getMontototal());
        verify(facturaCompraService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(facturaCompraService.findOne(2)).thenReturn(null);
        ResponseEntity<Facturacompra> respuesta = facturaCompraController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(facturaCompraService.save(any(Facturacompra.class))).thenReturn(facturacompra);
        ResponseEntity<Facturacompra> respuesta = facturaCompraController.save(facturacompra);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("150",respuesta.getBody().getMontototal());
    }

    @Test
    public void testUpdateExistente(){
        when(facturaCompraService.update(eq(1),any(Facturacompra.class))).thenReturn(facturacompra);
        ResponseEntity<Facturacompra> respuesta = facturaCompraController.update(1, facturacompra);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(facturaCompraService.update(eq(2),any(Facturacompra.class))).thenReturn(null);
        ResponseEntity<Facturacompra> respuesta = facturaCompraController.update(2, facturacompra);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(facturaCompraService).delete(1);
        ResponseEntity<Void> respuesta = facturaCompraController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(facturaCompraService,times(1)).delete(1);
    }
}
