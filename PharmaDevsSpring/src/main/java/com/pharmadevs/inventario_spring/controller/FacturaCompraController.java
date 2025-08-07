package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Facturacompra;
import com.pharmadevs.inventario_spring.model.Facturaventa;
import com.pharmadevs.inventario_spring.service.FacturaCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Facturaventa")
public class FacturaCompraController {
    @Autowired
    private FacturaCompraService facturaCompraService;

    @GetMapping
    public ResponseEntity<List<Facturacompra>> findAll(){
        List<Facturacompra>facturacompras=facturaCompraService.findAll();
        return ResponseEntity.ok(facturacompras);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Facturacompra> findOne(@PathVariable int id){
        Facturacompra facturacompra= facturaCompraService.findOne(id);
        if (facturacompra == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturacompra);
    }

    @PostMapping
    public ResponseEntity<Facturacompra> save(@RequestBody Facturacompra facturacompra){
        Facturacompra facturacompraNueva = facturaCompraService.save(facturacompra);
        return ResponseEntity.ok(facturacompraNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facturacompra> update(@PathVariable int id , @RequestBody Facturacompra facturacompra){
        Facturacompra facturacompraActualizada = facturaCompraService.update(id, facturacompra);
        if (facturacompraActualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturacompraActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        facturaCompraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
