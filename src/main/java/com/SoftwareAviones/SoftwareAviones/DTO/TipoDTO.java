package com.SoftwareAviones.SoftwareAviones.DTO;

import java.util.List;

import lombok.Data;

@Data
public class TipoDTO {

    private Integer id_tipo;
    private String tipo_avion;
    private List<String> aviones;
    
}
