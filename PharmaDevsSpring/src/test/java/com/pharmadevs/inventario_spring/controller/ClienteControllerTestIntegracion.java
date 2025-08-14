package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Cliente;
import com.pharmadevs.inventario_spring.service.ClienteService;
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

@WebMvcTest(ClienteController.class)
public class ClienteControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Cliente cliente = new Cliente(1,"Michael","Tumbaco","0988389144","example@gmail.com","1751887774001");
        Mockito.when(clienteService.findAll()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/Cliente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Michael"));

    }


    @Test
    public void testsave() throws  Exception {
        Cliente cliente = new Cliente(0,"Michael","Tumbaco","0988389144","example@gmail.com","1751887774001");

        Mockito.when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/Cliente").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Michael"));

    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Cliente/1")).andExpect(status().isNoContent());
    }
}
