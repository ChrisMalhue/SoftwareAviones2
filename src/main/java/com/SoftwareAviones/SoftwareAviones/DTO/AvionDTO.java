package com.SoftwareAviones.SoftwareAviones.DTO;

import java.util.List;

import lombok.Data;

@Data
public class AvionDTO {

    private Integer ID_avion;
    private String matricula;
    private String marca;
    private String modelo;
    private Integer capacidad_pasajero;
    private Double capacidad_carga_kg;
    private Double envergadura_metros;
    private Double capacidad_combustible;
    private String fabricante;
    private String tipo;
    private String origen;
    private List<String> pilotos;
    private List<String> vuelos;

}
