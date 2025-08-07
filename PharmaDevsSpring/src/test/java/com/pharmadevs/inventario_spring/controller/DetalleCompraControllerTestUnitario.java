package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Detallefacturacompra;
import com.pharmadevs.inventario_spring.service.DetalleCompraService;
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

public class DetalleCompraControllerTestUnitario {
    @InjectMocks
    private DetalleCompraController detalleCompraController;

    @Mock
    private DetalleCompraService detalleCompraService;

    private Detallefacturacompra detallefacturacompra;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        detallefacturacompra = new Detallefacturacompra();
        detallefacturacompra.setIdDetallecompra(1);
        detallefacturacompra.setIva("14");
        detallefacturacompra.setPreciounitario(150F);
        detallefacturacompra.setCantidad("150");
    }

    @Test
    public void testFindAll(){
        when(detalleCompraService.findAll()).thenReturn(List.of(detallefacturacompra));
        ResponseEntity<List<Detallefacturacompra>> respuesta = detalleCompraController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(detalleCompraService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(detalleCompraService.findOne(1)).thenReturn(detallefacturacompra);
        ResponseEntity<Detallefacturacompra> respuesta = detalleCompraController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(detallefacturacompra.getIva(),respuesta.getBody().getIva());
        verify(detalleCompraService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(detalleCompraService.findOne(2)).thenReturn(null);
        ResponseEntity<Detallefacturacompra> respuesta = detalleCompraController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(detalleCompraService.save(any(Detallefacturacompra.class))).thenReturn(detallefacturacompra);
        ResponseEntity<Detallefacturacompra> respuesta = detalleCompraController.save(detallefacturacompra);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("14",respuesta.getBody().getIva());
    }

    @Test
    public void testUpdateExistente(){
        when(detalleCompraService.update(eq(1),any(Detallefacturacompra.class))).thenReturn(detallefacturacompra);
        ResponseEntity<Detallefacturacompra> respuesta = detalleCompraController.update(1, detallefacturacompra);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(detalleCompraService.update(eq(2),any(Detallefacturacompra.class))).thenReturn(null);
        ResponseEntity<Detallefacturacompra> respuesta = detalleCompraController.update(2, detallefacturacompra);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(detalleCompraService).delete(1);
        ResponseEntity<Void> respuesta = detalleCompraController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(detalleCompraService,times(1)).delete(1);
    }
}
