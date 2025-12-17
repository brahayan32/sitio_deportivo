package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.TarifaRequestDTO;
import com.proyecto.citiodeportivo.dto.TarifaResponseDTO;
import com.proyecto.citiodeportivo.entities.TarifaEntity;
import com.proyecto.citiodeportivo.repository.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TarifaMapper {

    private final AdministradorRepository adminRepo;

    public TarifaEntity toEntity(TarifaRequestDTO dto) {
        TarifaEntity t = new TarifaEntity();
        t.setTipoServicio(dto.getTipoServicio());
        t.setPrecioHora(dto.getPrecioHora());
        t.setVigente(dto.getVigente());

        if (dto.getIdAdmin() != null) {
            t.setCreadoPorAdmin(
                    adminRepo.findById(dto.getIdAdmin())
                            .orElseThrow(() -> new RuntimeException("Admin no encontrado"))
            );
        }

        return t;
    }

    public TarifaResponseDTO toResponse(TarifaEntity t) {
        TarifaResponseDTO dto = new TarifaResponseDTO();
        dto.setIdTarifa(t.getIdTarifa());
        dto.setTipoServicio(t.getTipoServicio());
        dto.setPrecioHora(t.getPrecioHora());
        dto.setVigente(t.getVigente());
        dto.setCreadoEn(t.getCreadoEn());
        dto.setIdAdmin(
                t.getCreadoPorAdmin() != null ? t.getCreadoPorAdmin().getIdAdmin() : null
        );
        return dto;
    }
}
