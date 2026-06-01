package com.SoftweareAviones.SoftweareAviones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftweareAviones.SoftweareAviones.DTO.CursoDTO;
import com.SoftweareAviones.SoftweareAviones.model.Curso;
import com.SoftweareAviones.SoftweareAviones.model.Cursos;
import com.SoftweareAviones.SoftweareAviones.repository.CursoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoDTO> obtenerTodos() {
        return cursoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CursoDTO buscarPorId(Integer id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Curso no encontrado!"));

        return convertirADTO(curso);
    }

    public String eliminar(Integer id) {
        try {

            Curso curso = cursoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(
                            "¡Imposible eliminar! El curso con ID " + id + " no existe."
                    ));

            cursoRepository.delete(curso);

            return "El curso '" + curso.getNombre_curso() + "' fue eliminado correctamente.";

        } catch (RuntimeException e) {

            return e.getMessage();
        }
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso actualizarCurso(Integer id, Curso cursoActualizado) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        curso.setNombre_curso(cursoActualizado.getNombre_curso());

        return cursoRepository.save(curso);
    }

    private CursoDTO convertirADTO(Curso curso) {
        CursoDTO dto = new CursoDTO();

        dto.setID_curso(curso.getID_curso());
        dto.setNombre_curso(curso.getNombre_curso());

        List<String> pilotos = new ArrayList<>();

        if (curso.getPilotosCurso() != null) {

            for (Cursos cursoPiloto : curso.getPilotosCurso()) {

                pilotos.add(
                        cursoPiloto.getPiloto().getNombre()
                );
            }
        }

        dto.setPilotos(pilotos);

        return dto;
    }

}
