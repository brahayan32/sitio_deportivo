package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.SolicitudEntrenamientoRequestDTO;
import com.proyecto.citiodeportivo.dto.SolicitudEntrenamientoResponseDTO;
import com.proyecto.citiodeportivo.entities.SolicitudEntrenamientoEntity;
import com.proyecto.citiodeportivo.repository.ClienteRepository;
import com.proyecto.citiodeportivo.repository.EntrenadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitudEntrenamientoMapper {

    private final ClienteRepository clienteRepo;
    private final EntrenadorRepository entrenadorRepo;

    public SolicitudEntrenamientoEntity toEntity(SolicitudEntrenamientoRequestDTO dto) {
        SolicitudEntrenamientoEntity s = new SolicitudEntrenamientoEntity();

        s.setCliente(clienteRepo.findById(dto.getIdCliente()).orElse(null));

        s.setEntrenador(
                dto.getIdEntrenador() != null ?
                        entrenadorRepo.findById(dto.getIdEntrenador()).orElse(null)
                        : null
        );

        s.setInicio(dto.getInicio());
        s.setFin(dto.getFin());
        s.setEstado(dto.getEstado());

        return s;
    }

    public SolicitudEntrenamientoResponseDTO toResponse(SolicitudEntrenamientoEntity s) {
        SolicitudEntrenamientoResponseDTO dto = new SolicitudEntrenamientoResponseDTO();

        dto.setIdSolicitud(s.getIdSolicitud());
        dto.setIdCliente(s.getCliente().getIdCliente());
        dto.setIdEntrenador(
                s.getEntrenador() != null ? s.getEntrenador().getIdEntrenador() : null
        );
        dto.setInicio(s.getInicio());
        dto.setFin(s.getFin());
        dto.setEstado(s.getEstado());
        dto.setCreadoEn(s.getCreadoEn());

        return dto;
    }
}
