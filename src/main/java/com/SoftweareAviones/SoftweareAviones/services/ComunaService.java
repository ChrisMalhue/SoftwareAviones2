package com.SoftweareAviones.SoftweareAviones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftweareAviones.SoftweareAviones.DTO.ComunaDTO;
import com.SoftweareAviones.SoftweareAviones.model.Aerodromo;
import com.SoftweareAviones.SoftweareAviones.model.Comuna;
import com.SoftweareAviones.SoftweareAviones.repository.ComunaRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<ComunaDTO> obtenerTodos(){
        return comunaRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();
    }

    public ComunaDTO buscarPorId(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Comuna no encontrada!"));
        return convertirADTO(comuna);
    }

    public String eliminar(Integer id) {
        try {
            Comuna comuna = comunaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La Comuna con ID " + id + " no existe."));
            comunaRepository.delete(comuna);
            return "La Comuna'" + comuna.getComuna() + "' ha sido retirado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Comuna guardarComuna(Comuna comuna) {
        return comunaRepository.save(comuna);
    }


    public Comuna actualizarComuna(Integer id, Comuna comunaActualizado) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));

        comuna.setComuna(comunaActualizado.getComuna());

        comuna.setRegion(comunaActualizado.getRegion());

        return comunaRepository.save(comuna);
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();

        dto.setID_comuna(comuna.getID_comuna());
        dto.setComuna(comuna.getComuna());

        List<String> nombresAerodromos = new ArrayList<>();
        if (comuna.getAerodromos() != null) {
            for (Aerodromo aerodromo : comuna.getAerodromos()) {
                nombresAerodromos.add(
                    aerodromo.getNombre_aerodromo()
                );
            }
        }

        dto.setAerodromos(nombresAerodromos);

        if (comuna.getRegion() != null) {
            dto.setRegion(
                comuna.getRegion().getRegion()
            );
        }

        return dto;
    }


}
//ID_comuna - comuna - aerodromos - region
