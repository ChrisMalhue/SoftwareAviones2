package com.SoftwareAviones.SoftwareAviones.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tipos")

public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo;

    @NotBlank(message = "Debe especificarse el tipo del avion")
    @Size(min = 5, max = 15, message = "El tipo de avion debe tener de 5 a 15 caracteres")
    @Column(nullable = false, unique = true, length = 15)
    private String tipo_avion;

    @ManyToOne
    @JoinColumn(name = "ID_avion")
    private Avion avion;


}
