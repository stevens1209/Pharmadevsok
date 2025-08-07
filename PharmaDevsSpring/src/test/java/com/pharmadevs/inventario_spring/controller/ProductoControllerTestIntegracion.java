package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Producto;
import com.pharmadevs.inventario_spring.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Producto producto = new Producto(1,"Blinker","Antiparasitario","Desparasitantes","unidades",10.99F);
        Mockito.when(productoService.findAll()).thenReturn(List.of(producto));

        mockMvc.perform(get("/Producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Blinker"));

    }


    @Test
    public void testsave() throws  Exception {
        Producto producto = new Producto(1,"Blinker","Antiparasitario","Desparasitantes","unidades",10.99F);

        Mockito.when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/Producto").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Blinker"));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Producto/1")).andExpect(status().isNoContent());
    }
}
