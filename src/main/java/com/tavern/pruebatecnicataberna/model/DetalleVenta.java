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
    @ManyToOne
    private Venta venta;

    //Producto
    @ManyToOne
    private Producto producto;
    private Integer cantidadProducto;
    private Double precio;

    public Double getSubtotal() {
        return cantidadProducto*precio;
    }
}