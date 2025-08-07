package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Ventas;
import com.pharmadevs.inventario_spring.service.VentasService;
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

public class VentasControllerTestUnitario {
    @InjectMocks
    private VentasController ventasController;

    @Mock
    private VentasService ventasService;

    private Ventas ventas;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        ventas = new Ventas();
        ventas.setIdVentas(1);
        ventas.setFechaventa(new Date());
        ventas.setFormapago("Efectivo");
        ventas.setTotalventa("150");
    }

    @Test
    public void testFindAll(){
        when(ventasService.findAll()).thenReturn(List.of(ventas));
        ResponseEntity<List<Ventas>> respuesta = ventasController.findALL();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(ventasService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(ventasService.findOne(1)).thenReturn(ventas);
        ResponseEntity<Ventas> respuesta = ventasController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(ventas.getFormapago(),respuesta.getBody().getFormapago());
        verify(ventasService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(ventasService.findOne(2)).thenReturn(null);
        ResponseEntity<Ventas> respuesta = ventasController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(ventasService.save(any(Ventas.class))).thenReturn(ventas);
        ResponseEntity<Ventas> respuesta = ventasController.save(ventas);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Efectivo",respuesta.getBody().getFormapago());
    }

    @Test
    public void testUpdateExistente(){
        when(ventasService.update(eq(1),any(Ventas.class))).thenReturn(ventas);
        ResponseEntity<Ventas> respuesta = ventasController.update(1, ventas);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(ventasService.update(eq(2),any(Ventas.class))).thenReturn(null);
        ResponseEntity<Ventas> respuesta = ventasController.update(2, ventas);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(ventasService).delete(1);
        ResponseEntity<Void> respuesta = ventasController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(ventasService,times(1)).delete(1);
    }
}
