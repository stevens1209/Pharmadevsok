package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Bodega;
import com.pharmadevs.inventario_spring.service.BodegaService;
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

@WebMvcTest(BodegaController.class)
public class BodegaControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BodegaService bodegaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Bodega bodega = new Bodega(1,"Frasco",150,new Date());
        Mockito.when(bodegaService.findAll()).thenReturn(List.of(bodega));

        mockMvc.perform(get("/Bodega"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombrematerial").value("Frasco"));

    }


    @Test
    public void testsave() throws  Exception {
        Bodega bodega = new Bodega(0,"Frasco",150,new Date());

        Mockito.when(bodegaService.save(any(Bodega.class))).thenReturn(bodega);

        mockMvc.perform(post("/Bodega").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bodega)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrematerial").value("Frasco"));

    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Bodega/1")).andExpect(status().isNoContent());
    }
}
