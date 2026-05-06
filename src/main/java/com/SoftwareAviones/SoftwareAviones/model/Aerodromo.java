package com.SoftwareAviones.SoftwareAviones.model;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;

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
@Table(name = "curso")
public class Aerodromo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_aerodromo;

    @NotBlank (message = "El nombre del aerodromo es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del aerodromo debe tener entre 5 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre_aerodromo;

    

}
