package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.DisponibilidadRequestDTO;
import com.proyecto.citiodeportivo.dto.DisponibilidadResponseDTO;
import com.proyecto.citiodeportivo.entities.DisponibilidadEntrenadorEntity;
import com.proyecto.citiodeportivo.entities.EntrenadorEntity;
import org.springframework.stereotype.Component;

@Component
public class DisponibilidadMapper {

    public DisponibilidadEntrenadorEntity toEntity(DisponibilidadRequestDTO dto, EntrenadorEntity entrenador) {
        DisponibilidadEntrenadorEntity d = new DisponibilidadEntrenadorEntity();
        d.setEntrenador(entrenador);
        d.setDiaSemana(dto.getDiaSemana());
        d.setHoraInicio(dto.getHoraInicio());
        d.setHoraFin(dto.getHoraFin());
        return d;
    }

    public DisponibilidadResponseDTO toDTO(DisponibilidadEntrenadorEntity d) {
        DisponibilidadResponseDTO dto = new DisponibilidadResponseDTO();
        dto.setIdDisponibilidad(d.getIdDisponibilidad());
        dto.setIdEntrenador(d.getEntrenador().getIdEntrenador());
        dto.setEntrenadorNombre(d.getEntrenador().getNombre());
        dto.setDiaSemana(d.getDiaSemana());
        dto.setHoraInicio(d.getHoraInicio());
        dto.setHoraFin(d.getHoraFin());
        return dto;
    }
}
