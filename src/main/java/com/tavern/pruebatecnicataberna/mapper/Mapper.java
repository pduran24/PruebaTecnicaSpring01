package com.tavern.pruebatecnicataberna.mapper;

import com.tavern.pruebatecnicataberna.dto.DetalleVentaDTO;
import com.tavern.pruebatecnicataberna.dto.ProductoDTO;
import com.tavern.pruebatecnicataberna.dto.SucursalDTO;
import com.tavern.pruebatecnicataberna.dto.VentaDTO;
import com.tavern.pruebatecnicataberna.model.Producto;
import com.tavern.pruebatecnicataberna.model.Sucursal;
import com.tavern.pruebatecnicataberna.model.Venta;

import java.util.stream.Collectors;


public class Mapper {

    //Mapeo de Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto producto) {
        if (producto == null) return null;

        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        var detalle = venta.getDetalleVenta().stream().map( detalleVenta ->
                DetalleVentaDTO.builder()
                        .id(detalleVenta.getId())
                        .nombreProducto(detalleVenta.getProducto().getNombre())
                        .cantidad(detalleVenta.getCantidadProducto())
                        .precio(detalleVenta.getPrecio())
                        .subtotal(detalleVenta.getSubtotal())
                        .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .map( DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .estado(venta.getEstado())
                .idSucursal(venta.getSucursal().getId())
                .detalle(detalle)
                .total(total)
                .build();
    }

    //Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal sucursal) {
        if (sucursal == null) return null;

        return SucursalDTO.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }
}
