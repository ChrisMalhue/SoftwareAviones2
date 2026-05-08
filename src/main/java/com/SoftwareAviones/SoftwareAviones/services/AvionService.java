package com.SoftwareAviones.SoftwareAviones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftwareAviones.SoftwareAviones.DTO.AvionDTO;
import com.SoftwareAviones.SoftwareAviones.model.Avion;
import com.SoftwareAviones.SoftwareAviones.model.Aviones;
import com.SoftwareAviones.SoftwareAviones.model.TipoAvion;
import com.SoftwareAviones.SoftwareAviones.model.Vuelo;
import com.SoftwareAviones.SoftwareAviones.repository.AvionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AvionService {

    @Autowired
    private AvionRepository avionRepository;

    public List<AvionDTO> obtenerTodos() {
        return avionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public AvionDTO buscarPorId(Integer id) {
        Avion avion = avionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Avión no encontrado!"));

        return convertirADTO(avion);
    }

    public String eliminar(Integer id) {
        try {
            Avion avion = avionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(
                            "¡Imposible eliminar! El avión con ID " + id + " no existe."
                    ));
            avionRepository.delete(avion);
            return "El avión con matrícula '" + avion.getMatricula() + "' fue eliminado correctamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private void validarTipo(Avion avion) {

        if (avion.getTipo() == null) {
            return;
        }

        TipoAvion tipo = avion.getTipo().getTipo();

        switch (tipo) {
            case PASAJERO:
                if (avion.getCapacidad_pasajero() == null) {
                    throw new RuntimeException(
                        "Los aviones de pasajeros deben tener capacidad de pasajeros."
                    );
                }
                avion.setCapacidad_carga_kg(null);
                avion.setAlcance_km(null);
                avion.setCantidad_asientos_vip(null);
                break;
            case GUERRA:
                if (avion.getAlcance_km() == null) {
                    throw new RuntimeException(
                            "Los aviones de guerra deben tener alcance."
                    );
                }
                avion.setCapacidad_pasajero(null);
                avion.setCapacidad_carga_kg(null);
                avion.setCantidad_asientos_vip(null);
                break;
            case CARGA:
                if (avion.getCapacidad_carga_kg() == null) {
                    throw new RuntimeException(
                            "Los aviones de carga deben tener capacidad de carga."
                    );
                }
                avion.setCapacidad_pasajero(null);
                avion.setAlcance_km(null);
                avion.setCantidad_asientos_vip(null);
                break;
            case PRIVADO:
                if (avion.getCantidad_asientos_vip() == null) {
                    throw new RuntimeException(
                            "Los aviones privados deben tener asientos VIP."
                    );
                }
                avion.setCapacidad_pasajero(null);
                avion.setCapacidad_carga_kg(null);
                avion.setAlcance_km(null);
                break;
        }
    }

    public Avion guardarAvion(Avion avion) {
        validarTipo(avion);
        return avionRepository.save(avion);
    }

    private AvionDTO convertirADTO(Avion avion) {
        AvionDTO dto = new AvionDTO();

        dto.setID_avion(avion.getID_avion());
        dto.setMatricula(avion.getMatricula());
        dto.setMarca(avion.getMarca());
        dto.setModelo(avion.getModelo());
        if (avion.getTipo() != null) {
            dto.setTipo(
                    avion.getTipo().getTipo().name()
            );
        }

        dto.setCapacidad_pasajero(avion.getCapacidad_pasajero());
        dto.setCapacidad_carga_kg(avion.getCapacidad_carga_kg());
        dto.setAlcance_km(avion.getAlcance_km());
        dto.setCantidad_asientos_vip(avion.getCantidad_asientos_vip());

        dto.setEnvergadura_metros(avion.getEnvergadura_metros());
        dto.setCapacidad_combustible(avion.getCapacidad_combustible());

        if (avion.getFabricante() != null) {
            dto.setFabricante(
                    avion.getFabricante().getNombre_fabricante()
            );
        }

        if (avion.getOrigen() != null) {
            dto.setOrigen(
                    avion.getOrigen().getPais_origen()
            );
        }

        List<String> pilotos = new ArrayList<>();
        if (avion.getPilotosAvion() != null) {
            for (Aviones avionPiloto : avion.getPilotosAvion()) {
                pilotos.add(
                        avionPiloto.getPiloto().getNombre()
                );
            }
        }
        dto.setPilotos(pilotos);

        List<String> vuelos = new ArrayList<>();
        if (avion.getVuelos() != null) {
            for (Vuelo vuelo : avion.getVuelos()) {

                vuelos.add(
                        vuelo.getNumero_vuelo()
                );
            }
        }
        dto.setNumvuelos(vuelos);

        return dto;
    }

    public Avion actualizarAvion(Integer id, Avion avionActualizado) {
        Avion avion = avionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avión no encontrado"));

        avion.setMatricula(avionActualizado.getMatricula());
        avion.setMarca(avionActualizado.getMarca());
        avion.setModelo(avionActualizado.getModelo());
        avion.setTipo(avionActualizado.getTipo());

        avion.setCapacidad_pasajero(avionActualizado.getCapacidad_pasajero());
        avion.setCapacidad_carga_kg(avionActualizado.getCapacidad_carga_kg());
        avion.setAlcance_km(avionActualizado.getAlcance_km());
        avion.setCantidad_asientos_vip(avionActualizado.getCantidad_asientos_vip());

        avion.setEnvergadura_metros(avionActualizado.getEnvergadura_metros());
        avion.setCapacidad_combustible(avionActualizado.getCapacidad_combustible());
        avion.setFabricante(avionActualizado.getFabricante());
        avion.setOrigen(avionActualizado.getOrigen());

        validarTipo(avion);

        return avionRepository.save(avion);
    }
}


