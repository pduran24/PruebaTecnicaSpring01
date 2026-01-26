package com.tavern.pruebatecnicataberna.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id;
    private String nombreProducto;
    private Integer cantidad;
    private Double precio;
    //Dato extra para optimizar cálculos futuros
    private Double subtotal;
}
