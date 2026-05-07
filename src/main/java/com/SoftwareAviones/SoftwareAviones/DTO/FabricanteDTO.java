package com.SoftwareAviones.SoftwareAviones.DTO;

import java.util.List;

import lombok.Data;

@Data
public class FabricanteDTO {

    private Integer id_fabricante;
    private String nombre_fabricante;
    private List<String> aviones;
    
}
