package com.SoftwareAviones.SoftwareAviones.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftwareAviones.SoftwareAviones.DTO.PilotoDTO;
import com.SoftwareAviones.SoftwareAviones.model.Aviones;
import com.SoftwareAviones.SoftwareAviones.model.Cursos;
import com.SoftwareAviones.SoftwareAviones.model.Piloto;
import com.SoftwareAviones.SoftwareAviones.repository.PilotoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PilotoService {

    @Autowired
    private PilotoRepository pilotoRepository;

    public List<PilotoDTO> obtenerTodos() {
    return pilotoRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();
    }

    public PilotoDTO buscarPorId(Integer id) {
    Piloto piloto = pilotoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Piloto no encontrado!"));
        return convertirADTO(piloto);
    }

    public String eliminar(Integer id) {
        try {
            Piloto piloto = pilotoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El piloto con ID " + id + " no existe."));
            pilotoRepository.delete(piloto);
            return "El piloto '" + piloto.getNombre() + "' ha sido retirado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Piloto guardarPiloto(Piloto piloto) {
        return pilotoRepository.save(piloto);
    }

    private PilotoDTO convertirADTO(Piloto piloto) {
        PilotoDTO dto = new PilotoDTO();

        dto.setID_piloto(piloto.getID_piloto());
        dto.setRut(piloto.getRut());
        dto.setNombre(piloto.getNombre());
        dto.setApellido(piloto.getApellido());
        dto.setFecha_nacimiento(piloto.getFecha_nacimiento());
        dto.setHoras_vuelo(piloto.getHoras_vuelo());

        List<String> nombresCursos = new ArrayList<>();
        if (piloto.getCursosAprendidos() != null) {
            for (Cursos cursoPiloto : piloto.getCursosAprendidos()) {
                nombresCursos.add(
                    cursoPiloto.getCurso().getNombre_curso()
                );
            }
        }

        dto.setCursosAprendidos(nombresCursos);

        List<String> nombresAviones = new ArrayList<>();
        if (piloto.getAvionesVolados() != null) {
            for (Aviones avionPiloto : piloto.getAvionesVolados()) {
                nombresAviones.add(
                    avionPiloto.getAvion().getModelo()
                );
            }
        }
        dto.setAvionesVolados(nombresAviones);
        return dto;
    }

    public List<PilotoDTO> buscarPorNombre(String nombre){
        return pilotoRepository.buscarPorNombre(nombre).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<PilotoDTO> buscarPilotosConHorasMinimas(Integer horasMinimas){
        return pilotoRepository.buscarPilotosConHorasMinimas(horasMinimas).stream()
                .map(this::convertirADTO)
                .toList();
    }
    
}
