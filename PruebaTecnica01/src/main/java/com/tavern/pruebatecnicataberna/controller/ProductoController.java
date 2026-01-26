package com.tavern.pruebatecnicataberna.controller;

import com.tavern.pruebatecnicataberna.dto.ProductoDTO;
import com.tavern.pruebatecnicataberna.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos(){

        return ResponseEntity.ok(productoService.getProductos());
    }

    @PostMapping()
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO){
        ProductoDTO creado = productoService.createProducto(productoDTO);

        return ResponseEntity.created(URI.create("/api/productos/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {

        return ResponseEntity.ok(productoService.updateProducto(id, productoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);

        return ResponseEntity.noContent().build();
    }
}