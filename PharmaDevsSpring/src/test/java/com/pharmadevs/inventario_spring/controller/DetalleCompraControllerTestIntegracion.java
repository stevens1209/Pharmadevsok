package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Detallefacturacompra;
import com.pharmadevs.inventario_spring.service.DetalleCompraService;
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

@WebMvcTest(DetalleCompraController.class)
public class DetalleCompraControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DetalleCompraService detalleCompraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Detallefacturacompra detallefacturacompra = new Detallefacturacompra(1,"150",15.99F,"15%",16.00F);
        Mockito.when(detalleCompraService.findAll()).thenReturn(List.of(detallefacturacompra));

        mockMvc.perform(get("/Detallefacturacompra"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cantidad").value("150"));

    }


    @Test
    public void testsave() throws  Exception {
        Detallefacturacompra detallefacturacompra = new Detallefacturacompra(1,"150",15.99F,"15%",16.00F);

        Mockito.when(detalleCompraService.save(any(Detallefacturacompra.class))).thenReturn(detallefacturacompra);

        mockMvc.perform(post("/Detallefacturaventa").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detallefacturacompra)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value("150"));

    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Detallefacturacompra/1")).andExpect(status().isNoContent());
    }
}
