package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.CanchaRequestDTO;
import com.proyecto.citiodeportivo.dto.CanchaResponseDTO;
import com.proyecto.citiodeportivo.entities.CanchaEntity;
import com.proyecto.citiodeportivo.repository.TarifaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CanchaMapper {

    private final TarifaRepository tarifaRepository;

    public CanchaEntity toEntity(CanchaRequestDTO dto) {
        CanchaEntity cancha = new CanchaEntity();
        cancha.setNombre(dto.getNombre());
        cancha.setTipo(dto.getTipo());
        cancha.setEstado(dto.getEstado());
        cancha.setDescripcion(dto.getDescripcion());

        if (dto.getIdTarifa() != null) {
            cancha.setTarifa(
                    tarifaRepository.findById(dto.getIdTarifa())
                            .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"))
            );
        }

        return cancha;
    }

    public CanchaResponseDTO toResponseDTO(CanchaEntity entity) {
        CanchaResponseDTO dto = new CanchaResponseDTO();
        dto.setIdCancha(entity.getIdCancha());
        dto.setNombre(entity.getNombre());
        dto.setTipo(entity.getTipo());
        dto.setEstado(entity.getEstado());
        dto.setDescripcion(entity.getDescripcion());

        if (entity.getTarifa() != null) {
            dto.setIdTarifa(entity.getTarifa().getIdTarifa());
            dto.setPrecioHora(entity.getTarifa().getPrecioHora());
        }

        return dto;
    }
}

