package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Proveedor;
import com.pharmadevs.inventario_spring.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> findALL(){
        List<Proveedor>proveedors=proveedorService.findAll();
        return ResponseEntity.ok(proveedors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> findOne(@PathVariable int id){
        Proveedor proveedor= proveedorService.findOne(id);
        if (proveedor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    public ResponseEntity<Proveedor> save(@RequestBody Proveedor proveedor){
        Proveedor proveedorNuevo = proveedorService.save(proveedor);
        return ResponseEntity.ok(proveedorNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable int id , @RequestBody Proveedor proveedor){
        Proveedor proveedorActualizado = proveedorService.update(id, proveedor);
        if (proveedorActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedorActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        proveedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
