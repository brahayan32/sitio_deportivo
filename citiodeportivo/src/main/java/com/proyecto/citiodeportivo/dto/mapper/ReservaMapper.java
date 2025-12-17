package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.ReservaRequestDTO;
import com.proyecto.citiodeportivo.dto.ReservaResponseDTO;
import com.proyecto.citiodeportivo.entities.ReservaEntity;
import com.proyecto.citiodeportivo.repository.AdministradorRepository;
import com.proyecto.citiodeportivo.repository.CanchaRepository;
import com.proyecto.citiodeportivo.repository.ClienteRepository;
import com.proyecto.citiodeportivo.repository.TarifaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservaMapper {

    private final ClienteRepository clienteRepository;
    private final CanchaRepository canchaRepository;
    private final TarifaRepository tarifaRepository;
    private final AdministradorRepository administradorRepository;

    public ReservaEntity toEntity(ReservaRequestDTO dto) {
        ReservaEntity r = new ReservaEntity();

        r.setCliente(
                clienteRepository.findById(dto.getClienteId())
                        .orElseThrow(() -> new RuntimeException("Cliente no existe"))
        );
        r.setCancha(canchaRepository.findById(dto.getCanchaId())
                .orElseThrow(() -> new RuntimeException("Cliente no existe"))
        );
        r.setTarifa(dto.getTarifaId() != null ?
                tarifaRepository.findById(dto.getTarifaId()).orElse(null) : null);

        r.setInicio(dto.getInicio());
        r.setFin(dto.getFin());
        r.setIncluirEntrenador(dto.getIncluirEntrenador());
        r.setEstado(dto.getEstado());
        r.setTotalPagar(dto.getTotalPagar());

        if (dto.getCreadoPorAdminId() != null) {
            r.setCreadoPorAdmin(
                    administradorRepository.findById(dto.getCreadoPorAdminId()).orElse(null)
            );
        }

        return r;
    }

    public ReservaResponseDTO toResponse(ReservaEntity r) {
        ReservaResponseDTO dto = new ReservaResponseDTO();

        dto.setIdReserva(r.getIdReserva());
        dto.setClienteId(r.getCliente().getIdCliente());
        dto.setCanchaId(r.getCancha().getIdCancha());
        dto.setTarifaId(r.getTarifa() != null ? r.getTarifa().getIdTarifa() : null);
        dto.setInicio(r.getInicio());
        dto.setFin(r.getFin());
        dto.setIncluirEntrenador(r.getIncluirEntrenador());
        dto.setEstado(r.getEstado());
        dto.setTotalPagar(r.getTotalPagar());
        dto.setCreadoEn(r.getCreadoEn());
        dto.setCreadoPorAdminId(
                r.getCreadoPorAdmin() != null ? r.getCreadoPorAdmin().getIdAdmin() : null
        );

        return dto;
    }
}
