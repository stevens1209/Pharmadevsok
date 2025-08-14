package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Facturaventa;
import com.pharmadevs.inventario_spring.service.FacturaVentaService;
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

@WebMvcTest(FacturaVentaController.class)
public class FacturaVentaControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacturaVentaService facturaVentaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Facturaventa facturaventa = new Facturaventa(1,new Date(),"19.99");
        Mockito.when(facturaVentaService.findAll()).thenReturn(List.of(facturaventa));

        mockMvc.perform(get("/Facturaventa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].montototal").value("19.99"));

    }


    @Test
    public void testsave() throws  Exception {
        Facturaventa facturaventa = new Facturaventa(0,new Date(),"19.99");

        Mockito.when(facturaVentaService.save(any(Facturaventa.class))).thenReturn(facturaventa);

        mockMvc.perform(post("/Facturaventa").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(facturaventa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.montototal").value("19.99"));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Facturaventa/1")).andExpect(status().isNoContent());
    }
}
