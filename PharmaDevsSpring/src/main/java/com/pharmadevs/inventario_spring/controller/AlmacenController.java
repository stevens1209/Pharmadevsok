package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Almacen;
import com.pharmadevs.inventario_spring.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacen")
public class AlmacenController {
    @Autowired
    private AlmacenService almacenService;

    @GetMapping
    public ResponseEntity<List<Almacen>> findAll(){
        List<Almacen>almacens=almacenService.findAll();
        return ResponseEntity.ok(almacens);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Almacen> findOne(@PathVariable int id){
        Almacen almacen= almacenService.findOne(id);
        if (almacen == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(almacen);
    }

    @PostMapping
    public ResponseEntity<Almacen> save(@RequestBody Almacen almacen){
        Almacen almacenNuevo = almacenService.save(almacen);
        return ResponseEntity.ok(almacenNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> update(@PathVariable int id , @RequestBody Almacen almacen){
        Almacen almacenActualizado = almacenService.update(id, almacen);
        if (almacenActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(almacenActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        almacenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}