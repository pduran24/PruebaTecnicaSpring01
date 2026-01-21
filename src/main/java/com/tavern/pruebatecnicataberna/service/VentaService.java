package com.tavern.pruebatecnicataberna.service;

import com.tavern.pruebatecnicataberna.dto.VentaDTO;
import com.tavern.pruebatecnicataberna.exception.NotFoundException;
import com.tavern.pruebatecnicataberna.mapper.Mapper;
import com.tavern.pruebatecnicataberna.model.DetalleVenta;
import com.tavern.pruebatecnicataberna.model.Producto;
import com.tavern.pruebatecnicataberna.model.Sucursal;
import com.tavern.pruebatecnicataberna.model.Venta;
import com.tavern.pruebatecnicataberna.repository.ProductoRepository;
import com.tavern.pruebatecnicataberna.repository.SucursalRepository;
import com.tavern.pruebatecnicataberna.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<VentaDTO> getVentas() {
        return ventaRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public VentaDTO createVenta(VentaDTO ventaDto) {

        //Validaciones
        validarVentaDto(ventaDto);

        //Buscar la sucursal
        Sucursal sucursal = sucursalRepository.findById(ventaDto.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        //Crear la venta
        Venta venta = Venta.builder()
                .fecha(ventaDto.getFecha())
                .estado(ventaDto.getEstado())
                .sucursal(sucursal)
                .total(ventaDto.getTotal())
                .build();

        //Lista de detalles
        List<DetalleVenta> detalles = ventaDto.getDetalle().stream()
                .map(detalleVentaDTO -> {
                    //Buscar el producto
                    Producto p = productoRepository.findByNombre(detalleVentaDTO.getNombreProducto())
                            .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
                    //Construir el detalleVenta
                    return DetalleVenta.builder()
                            .producto(p)
                            .cantidadProducto(detalleVentaDTO.getCantidad())
                            .precio(detalleVentaDTO.getPrecio())
                            .venta(venta)
                            .build();
                }).toList();

        //Asignar la lista de detalles a venta
        venta.setDetalleVenta(detalles);

        //Guardar en la BD
        Venta ventaGuardada = ventaRepository.save(venta);

        return Mapper.toDTO(ventaGuardada);
    }

    @Override
    public VentaDTO updateVenta(Long idVenta, VentaDTO ventaDto) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));
        validarVentaDto(ventaDto);

        if (ventaDto.getFecha() != null) {
            venta.setFecha(ventaDto.getFecha());
        }
        if (ventaDto.getEstado() != null) {
            venta.setEstado(ventaDto.getEstado());
        }
        if (ventaDto.getTotal() != null) {
            venta.setTotal(ventaDto.getTotal());
        }
        if (ventaDto.getIdSucursal() != null) {
            Sucursal suc = sucursalRepository.findById(ventaDto.getIdSucursal())
                    .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));
            venta.setSucursal(suc);
        }
        Venta ventaGuardada = ventaRepository.save(venta);

        return Mapper.toDTO(ventaGuardada);
    }

    @Override
    public void deleteVenta(Long idVenta) {

        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));
        ventaRepository.delete(venta);
    }

    private void validarVentaDto(VentaDTO ventaDto) {
        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Sucursal no encontrada");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty()) throw new RuntimeException("Detalle no es valido");
    }
}
