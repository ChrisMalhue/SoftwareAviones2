package com.SoftwareAviones.SoftwareAviones.DTO;

import java.util.List;

import lombok.Data;

@Data
public class OrigenDTO {

    private Integer id_origen;
    private String marca;
    private String pais_origen;
    private List<String> aviones;

}
