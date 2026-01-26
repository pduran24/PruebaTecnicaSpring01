package com.tavern.pruebatecnicataberna.service;

import com.tavern.pruebatecnicataberna.dto.VentaDTO;

import java.util.List;

public interface IVentaService {
    List<VentaDTO> getVentas();
    VentaDTO createVenta(VentaDTO ventaDto);
    VentaDTO updateVenta(Long idVenta, VentaDTO ventaDto);
    void deleteVenta(Long idVenta);
}
