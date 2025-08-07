package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Almacen;
import com.pharmadevs.inventario_spring.service.AlmacenService;
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

public class AlmacenControllerTestUnitario {
    @InjectMocks
    private AlmacenController almacenController;

    @Mock
    private AlmacenService almacenService;

    private Almacen almacen;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        almacen = new Almacen();
        almacen.setIdAlmacen(1);
        almacen.setStock("150");
        almacen.setUbicacion("Puembo");
    }

    @Test
    public void testFindAll(){
        when(almacenService.findAll()).thenReturn(List.of(almacen));
        ResponseEntity<List<Almacen>> respuesta = almacenController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(almacenService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(almacenService.findOne(1)).thenReturn(almacen);
        ResponseEntity<Almacen> respuesta = almacenController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(almacen.getUbicacion(),respuesta.getBody().getUbicacion());
        verify(almacenService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(almacenService.findOne(2)).thenReturn(null);
        ResponseEntity<Almacen> respuesta = almacenController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(almacenService.save(any(Almacen.class))).thenReturn(almacen);
        ResponseEntity<Almacen> respuesta = almacenController.save(almacen);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Puembo",respuesta.getBody().getUbicacion());
    }

    @Test
    public void testUpdateExistente(){
        when(almacenService.update(eq(1),any(Almacen.class))).thenReturn(almacen);
        ResponseEntity<Almacen> respuesta = almacenController.update(1, almacen);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(almacenService.update(eq(2),any(Almacen.class))).thenReturn(null);
        ResponseEntity<Almacen> respuesta = almacenController.update(2, almacen);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(almacenService).delete(1);
        ResponseEntity<Void> respuesta = almacenController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(almacenService,times(1)).delete(1);
    }
}
