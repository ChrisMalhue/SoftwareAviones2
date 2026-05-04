package com.SoftwareAviones.SoftwareAviones.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aviones")
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_avion;

    @NotBlank (message = "La matricula es obligatoria")
    @Size(min = 2, max = 8, message = "La matricula debe tener entre 2 y 8 caracteres")
    @Pattern(regexp = "^(?:[A-Z]{1,2}-?[A-Z0-9]{1,5})$", message = "Formato de matricula inválido (Ej: CC-ABC, CC-1234, N12345)") // Validacion que funciona para ver el formato de la matricula del avion
    @Column(nullable = false,unique = true, length = 8)
    private String matricula;

    @NotBlank (message = "La marca del avion es obligatoria")
    @Size(min = 3, max = 30, message = "La marca debe contener entre 3 y 30 caracteres")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "La marca solo debe contener letras y espacios")
    @Column(nullable = false, length = 30)
    private String marca;

    @NotBlank (message = "El modelo es obligatorio")
    @Size(min = 2, max = 50, message = "El modelo debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9]+([\\- ][A-Za-z0-9]+)*$", message = "El modelo solo debe contener letras, numeros, espacios o guiones")
    @Column(nullable = false, length = 50)
    private String modelo;

    @Min(value = 0, message = "La capacidad de pasajeros no puede ser negativa")
    @Column(nullable = true)
    private Integer capacidad_pasajero;

    @Min(value = 0, message = "La capacidad de carga no puede ser negativa")
    @Column(nullable = true)
    private Double capacidad_carga_kg;

    @NotNull(message = "La envergadura es obligatoria")
    @Min(value = 0, message = "La envergadura no puede ser negativa")
    @Column(nullable = false)
    private Double envergadura_metros;

    @NotNull(message = "La capacidad de combustible es obligatoria")
    @Min(value = 0, message = "La capacidad de combustible no puede ser negativa")
    @Column(nullable = false)
    private Double capacidad_combustible;

    //@OneToMany(mappedBy = "avion")  // Esto debo hablarlo con el profe
    //private List<Vuelo> vuelos asd asdasdsa;
    
}
