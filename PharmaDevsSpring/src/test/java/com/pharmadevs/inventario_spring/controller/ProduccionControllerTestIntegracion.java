package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Produccion;
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

@WebMvcTest(ProduccionController.class)
public class ProduccionControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProduccionService produccionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
            Produccion produccion = new Produccion(1,new Date(),new Date(),"A2507001","150");
        Mockito.when(produccionService.findAll()).thenReturn(List.of(produccion));

        mockMvc.perform(get("/Produccion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lote").value("A2507001"));

    }


    @Test
    public void testsave() throws  Exception {
        Produccion produccion = new Produccion(1,new Date(),new Date(),"A2507001","150");


        Mockito.when(produccionService.save(any(Produccion.class))).thenReturn(produccion);

        mockMvc.perform(post("/Produccion").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produccion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lote").value("A2507001"));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Produccion/1")).andExpect(status().isNoContent());
    }
}
