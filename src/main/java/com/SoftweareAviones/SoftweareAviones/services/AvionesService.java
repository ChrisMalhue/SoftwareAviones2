package com.SoftweareAviones.SoftweareAviones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftweareAviones.SoftweareAviones.DTO.AvionesDTO;
import com.SoftweareAviones.SoftweareAviones.model.Aviones;
import com.SoftweareAviones.SoftweareAviones.repository.AvionesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AvionesService { 

    @Autowired
    private AvionesRepository avionesRepository;

    public List<AvionesDTO> obtenerTodos() {
        return avionesRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public AvionesDTO buscarPorId(Integer id) {
        Aviones aviones = avionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Registro no encontrado!"));
        return convertirADTO(aviones);
    }    

    public String eliminar(Integer id) {
        try {
            Aviones aviones = avionesRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El registro con ID " + id + " no existe."));
            avionesRepository.delete(aviones);
            return "El registro fue eliminado correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Aviones guardarAviones(Aviones aviones) {
        return avionesRepository.save(aviones);
    }    

    public Aviones actualizarAviones(Integer id, Aviones avionesActualizado) {
        Aviones aviones = avionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        aviones.setPiloto(avionesActualizado.getPiloto());
        aviones.setAvion(avionesActualizado.getAvion());
        return avionesRepository.save(aviones);
    }

    private AvionesDTO convertirADTO(Aviones aviones) {
        AvionesDTO dto = new AvionesDTO();
        dto.setID_aviones(aviones.getID_aviones());
        if (aviones.getPiloto() != null) {

            dto.setPiloto(
                aviones.getPiloto().getNombre()
            );
        }

        if (aviones.getAvion() != null) {
            dto.setAvion(
                aviones.getAvion().getModelo()
            );
        }

        return dto;
        
    }

}    
    

