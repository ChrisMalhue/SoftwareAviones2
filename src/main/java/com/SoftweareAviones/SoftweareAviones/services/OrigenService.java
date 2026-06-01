package com.SoftweareAviones.SoftweareAviones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftweareAviones.SoftweareAviones.DTO.OrigenDTO;
import com.SoftweareAviones.SoftweareAviones.model.Origen;
import com.SoftweareAviones.SoftweareAviones.repository.OrigenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrigenService {

    @Autowired
    private OrigenRepository origenRepository;

    public List<OrigenDTO> obtenerTodos() {
        return origenRepository.findAll().stream()
        .map(this::convertirADTO)
        .toList();
    }

    public OrigenDTO buscarPorId(Integer id) {
        Origen origen = origenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Origen no encontrado!"));
        return convertirADTO(origen);
    }
    
    public Origen guardarOrigen(Origen origen){
        return origenRepository.save(origen);
    }

    //actualizar 
    public Origen actualizarOrigen(Integer id, Origen origenActualizado) {

        Origen origen = origenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Origen no encontrado"));

        
        origen.setPais_origen(origenActualizado.getPais_origen());
        return origenRepository.save(origen);
    }

    //eliminar 
    public String eliminar(Integer id) {
        try {
            Origen origen = origenRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El Origen con ID " + id + " no existe."));
            origenRepository.delete(origen);
            return "El Origen '" + origen.getId_origen() + "' ha sido retirado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private OrigenDTO convertirADTO(Origen origen) {

        OrigenDTO dto = new OrigenDTO();
        
        dto.setId_origen(origen.getId_origen());
        dto.setPais_origen(origen.getPais_origen());
        return dto;
    }
}
