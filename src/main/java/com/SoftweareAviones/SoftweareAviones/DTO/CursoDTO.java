package com.SoftweareAviones.SoftweareAviones.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CursoDTO {

    private Integer ID_curso;
    private String nombre_curso;
    private List<String> pilotos;

}
