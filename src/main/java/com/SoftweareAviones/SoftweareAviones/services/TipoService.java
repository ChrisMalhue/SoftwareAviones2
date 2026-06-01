package com.SoftweareAviones.SoftweareAviones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftweareAviones.SoftweareAviones.DTO.TipoDTO;
import com.SoftweareAviones.SoftweareAviones.model.Tipo;
import com.SoftweareAviones.SoftweareAviones.repository.TipoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    public List<TipoDTO> obtenerTodos() {
        return tipoRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();
    }

    public TipoDTO buscarPorId(Integer id) {
        Tipo tipo = tipoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Tipo no encontrado!"));
        return convertirADTO(tipo);
    }

    public Tipo guardarTipo(Tipo tipo){
        return tipoRepository.save(tipo);
    }

    public Tipo buscarID_Tipo(Integer id){
        return tipoRepository.findById(id).orElse(null);
    }
    //actualizar 
    public Tipo actualizarTipo(Integer id, Tipo tipoActualizado) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
 
        tipo.setTipo(tipoActualizado.getTipo());
        return tipoRepository.save(tipo);
    }
    //eliminar 
    public String eliminar(Integer id) {
        try {
            Tipo tipo = tipoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El Tipo con ID " + id + " no existe."));
            tipoRepository.delete(tipo);
            return "El Tipo'" + tipo.getId_tipo() + "' ha sido retirado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private TipoDTO convertirADTO(Tipo tipo) {

        TipoDTO dto = new TipoDTO();
        
        dto.setId_tipo(tipo.getId_tipo());

        if (tipo.getTipo() != null) {
            dto.setTipo(tipo.getTipo().name());
        }
        List<String> aviones = new ArrayList<>();

        if (tipo.getAviones() != null) {
            tipo.getAviones().forEach(avion -> {
                aviones.add(avion.getMatricula());
            });
        }

        dto.setAviones(aviones);

        return dto;
    }

}

