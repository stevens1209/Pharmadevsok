package com.pharmadevs.inventario_spring.controller;

import com.pharmadevs.inventario_spring.model.Detallefacturacompra;
import com.pharmadevs.inventario_spring.service.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DetalleCompra")
public class DetalleCompraController {
    @Autowired
    private DetalleCompraService detalleCompraService;

    @GetMapping
    public ResponseEntity<List<Detallefacturacompra>> findAll(){
        List<Detallefacturacompra>detallefacturacompras=detalleCompraService.findAll();
        return ResponseEntity.ok(detallefacturacompras);
    }
    @GetMapping("/{id}")
        public ResponseEntity<Detallefacturacompra> findOne(@PathVariable int id){
        Detallefacturacompra detallefacturacompra= detalleCompraService.findOne(id);
        if (detallefacturacompra == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detallefacturacompra);
    }

    @PostMapping
    public ResponseEntity<Detallefacturacompra> save(@RequestBody Detallefacturacompra detallefacturacompra ){
        Detallefacturacompra detallefacturacompraNuevo = detalleCompraService.save(detallefacturacompra);
        return ResponseEntity.ok(detallefacturacompraNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detallefacturacompra> update(@PathVariable int id , @RequestBody Detallefacturacompra detallefacturacompra){
        Detallefacturacompra detallefacturacompraActualizada = detalleCompraService.update(id, detallefacturacompra);
        if (detallefacturacompraActualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detallefacturacompra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        detalleCompraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
