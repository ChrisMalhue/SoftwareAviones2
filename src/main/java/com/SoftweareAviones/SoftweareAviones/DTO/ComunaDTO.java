package com.SoftweareAviones.SoftweareAviones.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ComunaDTO {

    private Integer ID_comuna;
    private String comuna;
    private String region;
    private List<String> aerodromos;
    
}
