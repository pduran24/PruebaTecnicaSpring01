package com.tavern.pruebatecnicataberna.service;

import com.tavern.pruebatecnicataberna.dto.ProductoDTO;
import com.tavern.pruebatecnicataberna.exception.NotFoundException;
import com.tavern.pruebatecnicataberna.mapper.Mapper;
import com.tavern.pruebatecnicataberna.model.Producto;
import com.tavern.pruebatecnicataberna.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> getProductos() {
        return productoRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDto) {
        Producto prod = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .descripcion(productoDto.getDescripcion())
                .precio(productoDto.getPrecio())
                .stock(productoDto.getStock())
                .build();

        return Mapper.toDTO(productoRepository.save(prod));
    }

    @Override
    public ProductoDTO updateProducto(Long idProducto, ProductoDTO productoDto) {
        Producto prod = productoRepository.findById(idProducto)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        prod.setNombre(productoDto.getNombre());
        prod.setCategoria(productoDto.getCategoria());
        prod.setDescripcion(productoDto.getDescripcion());
        prod.setPrecio(productoDto.getPrecio());
        prod.setStock(productoDto.getStock());

        return Mapper.toDTO(productoRepository.save(prod));
    }

    @Override
    public void deleteProducto(Long idProducto) {
        if (!productoRepository.existsById(idProducto)) {
            throw new NotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(idProducto);
    }
}
