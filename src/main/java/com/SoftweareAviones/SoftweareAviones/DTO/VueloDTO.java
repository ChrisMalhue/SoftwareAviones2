package com.SoftweareAviones.SoftweareAviones.DTO;
import java.util.Date;

import lombok.Data;

@Data
public class VueloDTO {

    private Integer ID_vuelo;
    private String numero_vuelo;
    private Date hora_inicio_vuelo;
    private String tipo_vuelo;
    private String destino;
    private String piloto;
    private String avion;
    private String aerodromo;

}
