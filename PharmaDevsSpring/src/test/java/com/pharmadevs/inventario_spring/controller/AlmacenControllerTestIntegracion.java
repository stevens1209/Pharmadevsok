package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Almacen;
import com.pharmadevs.inventario_spring.service.AlmacenService;
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

@WebMvcTest(AlmacenController.class)
public class AlmacenControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlmacenService almacenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Almacen almacen = new Almacen(1,"100","Puembo");
        Mockito.when(almacenService.findAll()).thenReturn(List.of(almacen));

        mockMvc.perform(get("/Almacen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ubicacion").value("Puembo"));

    }


    @Test
    public void testsave() throws  Exception {
        Almacen almacen = new Almacen(0,"100","Puembo");

        Mockito.when(almacenService.save(any(Almacen.class))).thenReturn(almacen);

        mockMvc.perform(post("/Almacen").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(almacen)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ubicacion").value("Puembo"));

    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Detallefacturaventa/1")).andExpect(status().isNoContent());
    }
}
