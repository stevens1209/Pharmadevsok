package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Detallefacturaventa;
import com.pharmadevs.inventario_spring.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DetalleVenta")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public ResponseEntity<List<Detallefacturaventa>> findAll(){
        List<Detallefacturaventa>detallefacturaventas=detalleVentaService.findAll();
        return ResponseEntity.ok(detallefacturaventas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Detallefacturaventa> findOne(@PathVariable int id){
        Detallefacturaventa detallefacturaventa= detalleVentaService.findOne(id);
        if (detallefacturaventa == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detallefacturaventa);
    }

    @PostMapping
    public ResponseEntity<Detallefacturaventa> save(@RequestBody Detallefacturaventa detallefacturaventa){
        Detallefacturaventa detallefacturaventaNuevo = detalleVentaService.save(detallefacturaventa);
        return ResponseEntity.ok(detallefacturaventaNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detallefacturaventa> update(@PathVariable int id , @RequestBody Detallefacturaventa detallefacturaventa){
        Detallefacturaventa detallefacturaventaActualizada = detalleVentaService.update(id, detallefacturaventa);
        if (detallefacturaventaActualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detallefacturaventa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        detalleVentaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
