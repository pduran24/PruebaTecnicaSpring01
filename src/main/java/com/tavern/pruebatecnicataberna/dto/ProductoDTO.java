package com.tavern.pruebatecnicataberna.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String categoria;
    private String descripcion;
    private Double precio;
    private Integer stock;
}
