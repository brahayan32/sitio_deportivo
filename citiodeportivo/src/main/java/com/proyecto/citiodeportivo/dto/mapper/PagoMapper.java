package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.PagoRequestDTO;
import com.proyecto.citiodeportivo.dto.PagoResponseDTO;
import com.proyecto.citiodeportivo.entities.*;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public PagoEntity toEntity(PagoRequestDTO dto, ReservaEntity reserva, ClienteEntity cliente, AdministradorEntity admin) {
        PagoEntity p = new PagoEntity();
        p.setReserva(reserva);
        p.setCliente(cliente);
        p.setProcesadoPorAdmin(admin);
        p.setMetodo(dto.getMetodo());
        p.setMonto(dto.getMonto());
        p.setEstadoPago(dto.getEstadoPago());
        p.setFechaPago(java.time.LocalDateTime.now());
        return p;
    }

    public PagoResponseDTO toDTO(PagoEntity p) {
        PagoResponseDTO dto = new PagoResponseDTO();
        dto.setIdPago(p.getIdPago());
        dto.setMonto(p.getMonto());
        dto.setMetodo(p.getMetodo());
        dto.setEstadoPago(p.getEstadoPago());
        dto.setFechaPago(p.getFechaPago());

        if (p.getCliente() != null) dto.setIdCliente(p.getCliente().getIdCliente());
        if (p.getReserva() != null) dto.setIdReserva(p.getReserva().getIdReserva());
        if (p.getProcesadoPorAdmin() != null) dto.setProcesadoPorAdmin(p.getProcesadoPorAdmin().getIdAdmin());

        return dto;
    }
}
