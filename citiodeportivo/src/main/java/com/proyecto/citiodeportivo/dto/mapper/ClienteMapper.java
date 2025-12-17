package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.ClienteRequestDTO;
import com.proyecto.citiodeportivo.dto.ClienteResponseDTO;
import com.proyecto.citiodeportivo.entities.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteEntity toEntity(ClienteRequestDTO dto) {
        ClienteEntity c = new ClienteEntity();
        c.setNombre(dto.getNombre());
        c.setApellido(dto.getApellido());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        c.setPasswordHash(dto.getPassword()); // 🔥 Muy importante
        return c;
    }


    public ClienteResponseDTO toDTO(ClienteEntity c) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setIdCliente(c.getIdCliente());
        dto.setNombre(c.getNombre());
        dto.setApellido(c.getApellido());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());
        return dto;
    }
}
