package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.CanchaRequestDTO;
import com.proyecto.citiodeportivo.dto.CanchaResponseDTO;
import com.proyecto.citiodeportivo.entities.CanchaEntity;
import org.springframework.stereotype.Component;

@Component
public class CanchaMapper {

    public CanchaEntity toEntity(CanchaRequestDTO dto) {
        CanchaEntity cancha = new CanchaEntity();
        cancha.setNombre(dto.getNombre());
        cancha.setTipo(dto.getTipo());
        cancha.setEstado(dto.getEstado());
        cancha.setDescripcion(dto.getDescripcion());
        return cancha;
    }

    public CanchaResponseDTO toResponseDTO(CanchaEntity entity) {
        CanchaResponseDTO dto = new CanchaResponseDTO();
        dto.setIdCancha(entity.getIdCancha());
        dto.setNombre(entity.getNombre());
        dto.setTipo(entity.getTipo());
        dto.setEstado(entity.getEstado());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }
}
