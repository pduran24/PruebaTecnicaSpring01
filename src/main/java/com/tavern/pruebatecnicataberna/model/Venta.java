package com.tavern.pruebatecnicataberna.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String estado;

    @ManyToOne
    private Sucursal sucursal;

    @OneToMany (mappedBy = "venta")
    private List<DetalleVenta> detalleVenta = new ArrayList<>();

    private Double total;
}