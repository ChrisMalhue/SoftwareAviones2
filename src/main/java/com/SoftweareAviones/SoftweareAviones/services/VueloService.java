package com.SoftweareAviones.SoftweareAviones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftweareAviones.SoftweareAviones.DTO.VueloDTO;
import com.SoftweareAviones.SoftweareAviones.model.Vuelo;
import com.SoftweareAviones.SoftweareAviones.repository.VueloRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    public List<VueloDTO> obtenerTodos() {
        return vueloRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();
    }

    public VueloDTO buscarPorId(Integer id) {
        Vuelo vuelo = vueloRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Vuelo no encontrado!"));
        return convertirADTO(vuelo);
    }

    public String eliminar(Integer id) {
        try {
            Vuelo vuelo = vueloRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El piloto con ID " + id + " no existe."));
            vueloRepository.delete(vuelo);
            return "El vuelo '" + vuelo.getNumero_vuelo() + "' ha sido retirado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Vuelo agregarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    private VueloDTO convertirADTO(Vuelo vuelo) {
        VueloDTO dto = new VueloDTO();
        dto.setID_vuelo(vuelo.getID_vuelo());
        dto.setNumero_vuelo(vuelo.getNumero_vuelo());
        dto.setHora_inicio_vuelo(vuelo.getHora_inicio_vuelo());
        dto.setTipo_vuelo(vuelo.getTipo_vuelo());
        dto.setDestino(vuelo.getDestino());

        if (vuelo.getPiloto() != null) {
            dto.setPiloto(
                vuelo.getPiloto().getNombre()
            );
        }

        if (vuelo.getAvion() != null) {
            dto.setAvion(
                vuelo.getAvion().getModelo()
            );
        }

        if (vuelo.getAerodromo() != null) {
            dto.setAerodromo(
                vuelo.getAerodromo().getNombre_aerodromo()
            );
        }

        return dto;    
    }

    public Vuelo actualizarVuelo(Integer id, Vuelo vueloActualizado) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
                
        vuelo.setNumero_vuelo(vueloActualizado.getNumero_vuelo());
        vuelo.setHora_inicio_vuelo(vueloActualizado.getHora_inicio_vuelo());
        vuelo.setTipo_vuelo(vueloActualizado.getTipo_vuelo());
        vuelo.setDestino(vueloActualizado.getDestino());
        vuelo.setPiloto(vueloActualizado.getPiloto());
        vuelo.setAvion(vueloActualizado.getAvion());
        vuelo.setAerodromo(vueloActualizado.getAerodromo());

        return vueloRepository.save(vuelo);
    }

}
