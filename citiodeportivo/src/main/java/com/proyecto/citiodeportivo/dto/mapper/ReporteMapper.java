package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.ReporteRequestDTO;
import com.proyecto.citiodeportivo.dto.ReporteResponseDTO;
import com.proyecto.citiodeportivo.entities.ReporteEntity;
import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {

    public ReporteEntity toEntity(ReporteRequestDTO dto) {
        ReporteEntity r = new ReporteEntity();
        r.setTipoReporte(dto.getTipoReporte());
        r.setDescripcion(dto.getDescripcion());
        return r;
    }

    public ReporteResponseDTO toDTO(ReporteEntity e) {
        ReporteResponseDTO dto = new ReporteResponseDTO();
        dto.setIdReporte(e.getIdReporte());
        dto.setAdministradorId(e.getAdministrador() != null ? e.getAdministrador().getIdAdmin() : null);
        dto.setReservaId(e.getReserva() != null ? e.getReserva().getIdReserva() : null);
        dto.setFechaGenerado(e.getFechaGenerado());
        dto.setTipoReporte(e.getTipoReporte());
        dto.setDescripcion(e.getDescripcion());
        return dto;
    }
}
