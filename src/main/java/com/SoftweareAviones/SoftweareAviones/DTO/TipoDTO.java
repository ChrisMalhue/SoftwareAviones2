package com.SoftweareAviones.SoftweareAviones.DTO;

import java.util.List;

import lombok.Data;

@Data
public class TipoDTO {

    private Integer id_tipo;
    private String tipo;
    private List<String> aviones;
    
}
