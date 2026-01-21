package com.tavern.pruebatecnicataberna.controller;

import com.tavern.pruebatecnicataberna.dto.VentaDTO;
import com.tavern.pruebatecnicataberna.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping()
    public ResponseEntity<List<VentaDTO>> listarVentas() {
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @PostMapping()
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO actualizada = ventaService.createVenta(ventaDTO);

        return ResponseEntity.created(URI.create("/api/ventas/" + actualizada.getId())).body(actualizada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        VentaDTO actualizada = ventaService.updateVenta(id, ventaDTO);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);

        return ResponseEntity.noContent().build();
    }
}