package com.tavern.pruebatecnicataberna.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ventaId")
    private Venta venta;

    //Producto
    @ManyToOne
    @JoinColumn(name="productoId")
    private Producto producto;
    private Integer cantidadProducto;
    private Double precio;

    public Double getSubtotal() {
        return cantidadProducto*precio;
    }
}