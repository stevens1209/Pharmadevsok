package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Produccion;
import com.pharmadevs.inventario_spring.service.ProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Produccion")
public class ProduccionController {
    @Autowired
    private ProduccionService produccionService;

    @GetMapping
    public ResponseEntity<List<Produccion>> findALL(){
        List<Produccion>produccions=produccionService.findAll();
        return ResponseEntity.ok(produccions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produccion> findOne(@PathVariable int id){
        Produccion produccion= produccionService.findOne(id);
        if (produccion == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produccion);
    }

    @PostMapping
    public ResponseEntity<Produccion> save(@RequestBody Produccion produccion){
        Produccion produccionNuevo = produccionService.save(produccion);
        return ResponseEntity.ok(produccionNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produccion> update(@PathVariable int id , @RequestBody Produccion produccion){
        Produccion produccionActualizada = produccionService.update(id, produccion);
        if (produccionActualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produccionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        produccionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}