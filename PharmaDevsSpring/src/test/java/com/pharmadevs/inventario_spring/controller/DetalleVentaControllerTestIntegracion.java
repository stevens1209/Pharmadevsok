package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pharmadevs.inventario_spring.model.Detallefacturaventa;
import com.pharmadevs.inventario_spring.service.DetalleVentaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DetalleVentaController.class)
public class DetalleVentaControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DetalleVentaService detalleCompraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Detallefacturaventa detallefacturaventa = new Detallefacturaventa(1,150,15.99F,"15%",16.00F);
        Mockito.when(detalleCompraService.findAll()).thenReturn(List.of(detallefacturaventa));

        mockMvc.perform(get("/Detallefacturaventa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cantidad").value("150"));

    }


    @Test
    public void testsave() throws  Exception {
        Detallefacturaventa detallefacturaventa = new Detallefacturaventa(1,150,15.99F,"15%",16.00F);

        Mockito.when(detalleCompraService.save(any(Detallefacturaventa.class))).thenReturn(detallefacturaventa);

        mockMvc.perform(post("/Detallefacturaventa").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detallefacturaventa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value("150"));

    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Detallefacturaventa/1")).andExpect(status().isNoContent());
    }
}
