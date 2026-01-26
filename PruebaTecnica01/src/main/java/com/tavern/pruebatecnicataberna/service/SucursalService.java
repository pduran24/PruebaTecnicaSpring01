package com.tavern.pruebatecnicataberna.service;

import com.tavern.pruebatecnicataberna.dto.SucursalDTO;
import com.tavern.pruebatecnicataberna.exception.NotFoundException;
import com.tavern.pruebatecnicataberna.mapper.Mapper;
import com.tavern.pruebatecnicataberna.model.Sucursal;
import com.tavern.pruebatecnicataberna.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<SucursalDTO> getSucursales() {
        return sucursalRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursalDto) {
        Sucursal sur = Sucursal.builder()
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.toDTO(sucursalRepository.save(sur));
    }

    @Override
    public SucursalDTO updateSucursal(Long idSucursal, SucursalDTO sucursalDto) {
        Sucursal sur = sucursalRepository.findById(idSucursal)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        sur.setNombre(sucursalDto.getNombre());
        sur.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDTO(sucursalRepository.save(sur));
    }

    @Override
    public void deleteSucursal(Long idSucursal) {
        if  (!sucursalRepository.existsById(idSucursal)) {
            throw new NotFoundException("Sucursal no encontrada");
        }
        sucursalRepository.deleteById(idSucursal);
    }
}
