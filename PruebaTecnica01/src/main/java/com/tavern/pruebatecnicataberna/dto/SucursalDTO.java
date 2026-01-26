package com.tavern.pruebatecnicataberna.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SucursalDTO {
    private Long id;
    private String nombre;
    private String direccion;
}
