package com.tavern.pruebatecnicataberna.service;

import com.tavern.pruebatecnicataberna.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {
    List<ProductoDTO> getProductos();
    ProductoDTO createProducto(ProductoDTO productoDto);
    ProductoDTO updateProducto(Long idProducto, ProductoDTO productoDto);
    void deleteProducto(Long idProducto);
}
