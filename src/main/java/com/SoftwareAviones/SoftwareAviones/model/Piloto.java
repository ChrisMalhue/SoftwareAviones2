package com.SoftwareAviones.SoftwareAviones.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
@Table(name = "pilotos")
public class Piloto {
    // ID_piloto, RUT_piloto, Nombre, Apellido, Fecha_nacimiento, Horas_vuelo,
    //Fecha_inicio_vuelos, ID_avion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_piloto;

    @NotBlank (message = "El rut es obligatorio")
    @Size(min = 11, max = 12, message = "El rut debe tener entre 11 y 12 caracteres")
    @Column(nullable = false, length = 12)
    private String rut;

    @NotBlank (message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank (message = "El apellido es obligatorio")
    @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String apellido;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @Column(nullable = false)
    private Date Fecha_nacimiento;

    @NotNull(message = "Las horas de vuelo son obligatorias")
    @Min(value = 0, message = "Las horas de vuelo no pueden ser negativas")
    @Column(nullable = false)
    private Integer horas_vuelo;

    @ManyToOne
    @JoinColumn(name = "ID_avion")
    private Avion avion;



}
