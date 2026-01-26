package com.tavern.pruebatecnicataberna.controller;

import com.tavern.pruebatecnicataberna.dto.SucursalDTO;
import com.tavern.pruebatecnicataberna.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @GetMapping()
    public ResponseEntity<List<SucursalDTO>> listarSucursales() {
        return ResponseEntity.ok(sucursalService.getSucursales());
    }

    @PostMapping()
    public ResponseEntity<SucursalDTO> createSucursal(@RequestBody SucursalDTO sucursalDto) {
        SucursalDTO creada = sucursalService.createSucursal(sucursalDto);

        return ResponseEntity.created(URI.create("/api/sucursales/" + creada.getId())).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(@PathVariable Long id, @RequestBody SucursalDTO sucursalDto) {
        SucursalDTO actualizada = sucursalService.updateSucursal(id, sucursalDto);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);

        return ResponseEntity.noContent().build();
    }
}
