package com.SoftwareAviones.SoftwareAviones.DTO;

import java.util.List;

import lombok.Data;

@Data
public class RegionDTO {

    private Integer ID_region;
    private String region;
    private List<String> comunas;
    
}
