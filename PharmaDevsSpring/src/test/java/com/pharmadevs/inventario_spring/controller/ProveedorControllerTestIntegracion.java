package com.pharmadevs.inventario_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmadevs.inventario_spring.model.Proveedor;
import com.pharmadevs.inventario_spring.service.ProductoService;
import com.pharmadevs.inventario_spring.service.ProveedorService;
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

@WebMvcTest(ProveedorController.class)
public class ProveedorControllerTestIntegracion {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProveedorService proveedorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Proveedor proveedor= new Proveedor(1,"Guimo","quito","09999987","guimo@gmail.com","175545455454001");
        Mockito.when(proveedorService.findAll()).thenReturn(List.of(proveedor));

        mockMvc.perform(get("/Proveedor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Guimo"));

    }


    @Test
    public void testsave() throws  Exception {
        Proveedor proveedor= new Proveedor(1,"Guimo","quito","09999987","guimo@gmail.com","175545455454001");

        Mockito.when(proveedorService.save(any(Proveedor.class))).thenReturn(proveedor);

        mockMvc.perform(post("/Proveedor").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proveedor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Guimo"));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/Proveedor/1")).andExpect(status().isNoContent());
    }

}
