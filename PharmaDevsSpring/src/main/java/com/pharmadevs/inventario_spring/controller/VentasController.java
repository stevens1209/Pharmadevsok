package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Ventas;
import com.pharmadevs.inventario_spring.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ventas")
public class VentasController {
    @Autowired
    private VentasService ventasService;

    @GetMapping
    public ResponseEntity<List<Ventas>> findALL(){
        List<Ventas>ventas=ventasService.findAll();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ventas> findOne(@PathVariable int id){
        Ventas ventas= ventasService.findOne(id);
        if (ventas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<Ventas> save(@RequestBody Ventas ventas){
        Ventas ventasNuevo = ventasService.save(ventas);
        return ResponseEntity.ok(ventasNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ventas> update(@PathVariable int id , @RequestBody Ventas ventas){
        Ventas ventasActualizado = ventasService.update(id, ventas);
        if (ventasActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventasActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        ventasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
