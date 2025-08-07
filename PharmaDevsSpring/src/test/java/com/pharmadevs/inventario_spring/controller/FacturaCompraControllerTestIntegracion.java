package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Facturacompra;
import com.pharmadevs.inventario_spring.service.FacturaCompraService;
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

@WebMvcTest(FacturaCompraController.class)
public class FacturaCompraControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacturaCompraService facturaCompraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Facturacompra facturacompra = new Facturacompra(1,new Date(),"19.99");
        Mockito.when(facturaCompraService.findAll()).thenReturn(List.of(facturacompra));

        mockMvc.perform(get("/Facturacompra"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].montototal").value("19.99"));

    }


    @Test
    public void testsave() throws  Exception {
        Facturacompra facturacompra = new Facturacompra(0,new Date(),"19.99");

        Mockito.when(facturaCompraService.save(any(Facturacompra.class))).thenReturn(facturacompra);

        mockMvc.perform(post("/Facturacompra").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(facturacompra)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.montototal").value("19.99"));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Facturacompra/1")).andExpect(status().isNoContent());
    }
}
