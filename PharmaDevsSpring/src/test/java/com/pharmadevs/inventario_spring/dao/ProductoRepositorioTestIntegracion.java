package com.pharmadevs.inventario_spring.dao;


import com.pharmadevs.inventario_spring.model.Producto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ProductoRepositorioTestIntegracion {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Test
    public void findAll(){
        List<Producto> productos = productoRepositorio.findAll();
        assertNotNull(productos);
        assertTrue(productos.size() > 0);

        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Producto> producto = productoRepositorio.findById(1);
        assertTrue(producto.isPresent(),"El producto con id=1 deberia existir");
        System.out.println(producto.toString());
    }

    @Test
    public void save(){
        Producto producto = new Producto(0,"Novo-tears","Lagrimas descongestionantes","goteros","150 unidades",10.50F);
        productoRepositorio.save(producto);
        assertNotNull(producto.getIdProducto(),"El producto guaradado deberia tener un id");
        assertEquals("Novo-tears",producto.getNombre());
        assertEquals("Lagrimas descongestionantes",producto.getDescripcion());
        assertEquals("goteros",producto.getCategoria());
        assertEquals("150 unidades",producto.getUnidadMedida());
        assertEquals(15.50F,producto.getPreciobase());
    }

    @Test
    public void update() {
        Optional<Producto> producto = productoRepositorio.findById(1);

        assertTrue(producto.isPresent(), "El producto con id=1 deberia existir para ser actualizado");

        producto.orElse(null).setNombre("SinDlor");
        producto.orElse(null).setDescripcion("IBUPROFENO 400 y 600 mg CÁPSULAS LÍQUIDAS");
        producto.orElse(null).setCategoria("Analgesico");
        producto.orElse(null).setUnidadMedida("1500 unidades ");
        producto.orElse(null).setPreciobase(0.50F);


        Producto productoActuzalizado = productoRepositorio.save(producto.orElse(null));

        assertEquals("SinDlor", productoActuzalizado.getNombre());
        assertEquals("IBUPROFENO 400 y 600 mg CÁPSULAS LÍQUIDAS", productoActuzalizado.getDescripcion());
        assertEquals("Analgesico", productoActuzalizado.getCategoria());
        assertEquals("1500 unidades", productoActuzalizado.getUnidadMedida());
        assertEquals(0.50F, productoActuzalizado.getPreciobase());

    }

    @Test
    public void delete () {
        if (productoRepositorio.existsById(1)) {
            productoRepositorio.deleteById(1);
        }
        assertFalse(productoRepositorio.existsById(1), "El producto con id=1 deberia haberse eliminado");
    }
}
