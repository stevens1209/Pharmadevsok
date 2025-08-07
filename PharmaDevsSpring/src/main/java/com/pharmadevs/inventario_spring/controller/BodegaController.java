package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Bodega;
import com.pharmadevs.inventario_spring.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodega")
public class BodegaController {
    @Autowired
    private BodegaService bodegaService;

    @GetMapping
    public ResponseEntity<List<Bodega>> findAll(){
        List<Bodega>bodegas=bodegaService.findAll();
        return ResponseEntity.ok(bodegas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bodega> findOne(@PathVariable int id){
        Bodega bodega= bodegaService.findOne(id);
        if (bodega == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bodega);
    }

    @PostMapping
    public ResponseEntity<Bodega> save(@RequestBody Bodega bodega){
        Bodega bodegaNuevo = bodegaService.save(bodega);
        return ResponseEntity.ok(bodegaNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bodega> update(@PathVariable int id , @RequestBody Bodega bodega){
        Bodega bodegaActualizada = bodegaService.update(id, bodega);
        if (bodegaActualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bodegaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        bodegaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
