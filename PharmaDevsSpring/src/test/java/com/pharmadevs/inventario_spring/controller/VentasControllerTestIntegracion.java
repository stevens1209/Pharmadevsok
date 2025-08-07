package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Ventas;
import com.pharmadevs.inventario_spring.service.VentasService;
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

@WebMvcTest(VentasController.class)
public class VentasControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VentasService ventasService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Ventas ventas = new Ventas(1,new Date(),"Efectivo","150");
        Mockito.when(ventasService.findAll()).thenReturn(List.of(ventas));

        mockMvc.perform(get("/Ventas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].formapago").value("Efectivo"));

    }


    @Test
    public void testsave() throws  Exception {
        Ventas ventas = new Ventas(1,new Date(),"Efectivo","150");


        Mockito.when(ventasService.save(any(Ventas.class))).thenReturn(ventas);

        mockMvc.perform(post("/Ventas").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ventas)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formapago").value("Efectivo "));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Ventas/1")).andExpect(status().isNoContent());
    }
}
