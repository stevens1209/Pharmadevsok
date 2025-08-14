package com.pharmadevs.inventario_spring.controller;


import com.pharmadevs.inventario_spring.model.Facturaventa;
import com.pharmadevs.inventario_spring.service.FacturaVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FacturaVenta")

public class FacturaVentaController {
    @Autowired
    private FacturaVentaService facturaVentaService;

    @GetMapping
    public ResponseEntity<List<Facturaventa>> findALL(){
        List<Facturaventa>facturaventas=facturaVentaService.findAll();
        return ResponseEntity.ok(facturaventas);
    }

    @GetMapping("/{id}")
        public ResponseEntity<Facturaventa> findOne(@PathVariable int id){
        Facturaventa facturaventa= facturaVentaService.findOne(id);
        if (facturaventa == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaventa);
    }

    @PostMapping
    public ResponseEntity<Facturaventa> save(@RequestBody Facturaventa facturaventa){
        Facturaventa facturaventaNuevo = facturaVentaService.save(facturaventa);
        return ResponseEntity.ok(facturaventaNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facturaventa> update(@PathVariable int id , @RequestBody Facturaventa facturaventa){
        Facturaventa facturaventaActualizado = facturaVentaService.update(id, facturaventa);
        if (facturaventaActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaventaActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        facturaVentaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
