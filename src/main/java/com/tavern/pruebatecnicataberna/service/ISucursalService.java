package com.tavern.pruebatecnicataberna.service;

import com.tavern.pruebatecnicataberna.dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {

    List<SucursalDTO> getSucursales();
    SucursalDTO createSucursal(SucursalDTO sucursalDto);
    SucursalDTO updateSucursal(Long idSucursal, SucursalDTO sucursalDto);
    void deleteSucursal(Long idSucursal);
}
