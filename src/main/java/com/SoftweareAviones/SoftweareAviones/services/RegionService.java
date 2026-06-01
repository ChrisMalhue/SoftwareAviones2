package com.SoftweareAviones.SoftweareAviones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftweareAviones.SoftweareAviones.DTO.RegionDTO;
import com.SoftweareAviones.SoftweareAviones.model.Region;
import com.SoftweareAviones.SoftweareAviones.model.Comuna;
import com.SoftweareAviones.SoftweareAviones.model.Aerodromo;
import com.SoftweareAviones.SoftweareAviones.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodos() {
        return regionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RegionDTO buscarPorId(Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Se Pudo Encontrar La Region"));
        return convertirADTO(region);
    }

    public String eliminar(Integer id) {
        try {
            Region region = regionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("La región con ID " + id + " no existe"));
            regionRepository.delete(region);
            return "La región '" + region.getRegion() + "' se elimino correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Region guardarRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region actualizarRegion(Integer id, Region regionActualizada) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));

        region.setRegion(regionActualizada.getRegion());

        return regionRepository.save(region);
    }

    private RegionDTO convertirADTO(Region region) {
        RegionDTO dto = new RegionDTO();

        dto.setID_region(region.getID_region());

        dto.setRegion(region.getRegion());

        List<String> nombresComunas = new ArrayList<>();

        if (region.getComunas() != null) {
            for (Comuna comuna : region.getComunas()) {
                nombresComunas.add(comuna.getComuna());
            }
        }
        dto.setComunas(nombresComunas);
        return dto;
        
    }
}
