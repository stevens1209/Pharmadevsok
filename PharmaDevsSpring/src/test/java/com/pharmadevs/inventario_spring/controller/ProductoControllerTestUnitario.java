package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Producto;
import com.pharmadevs.inventario_spring.service.ProductoService;
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

public class ProductoControllerTestUnitario {
    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        producto = new Producto();
        producto.setIdProducto(1);
        producto.setCategoria("Antibiotico");
        producto.setNombre("Amoxicilina");
        producto.setDescripcion("Antibiotico de amplio aspectro");
        producto.setPreciobase(10.99F);
        producto.setUnidadMedida("blisters");
    }

    @Test
    public void testFindAll(){
        when(productoService.findAll()).thenReturn(List.of(producto));
        ResponseEntity<List<Producto>> respuesta = productoController.findALL();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(productoService,times(1)).findAll();
    }

    @Test
    public void testFinOneExistente(){
        when(productoService.findOne(1)).thenReturn(producto);
        ResponseEntity<Producto> respuesta = productoController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(producto.getNombre(),respuesta.getBody().getNombre());
        verify(productoService,times(1)).findOne(1);
    }

    @Test
    public void testFindOneNoExistente(){
        when(productoService.findOne(2)).thenReturn(null);
        ResponseEntity<Producto> respuesta = productoController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
        //verify(clienteService,times(0)).findOne(2);
    }

    @Test
    public void testSave(){
        when(productoService.save(any(Producto.class))).thenReturn(producto);
        ResponseEntity<Producto> respuesta = productoController.save(producto);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("blisters",respuesta.getBody().getUnidadMedida());
    }

    @Test
    public void testUpdateExistente(){
        when(productoService.update(eq(1),any(Producto.class))).thenReturn(producto);
        ResponseEntity<Producto> respuesta = productoController.update(1, producto);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(productoService.update(eq(2),any(Producto.class))).thenReturn(null);
        ResponseEntity<Producto> respuesta = productoController.update(2, producto);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(productoService).delete(1);
        ResponseEntity<Void> respuesta = productoController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(productoService,times(1)).delete(1);
    }
}
