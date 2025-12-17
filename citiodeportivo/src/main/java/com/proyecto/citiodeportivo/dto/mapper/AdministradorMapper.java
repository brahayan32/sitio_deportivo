package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.AdministradorRequestDTO;
import com.proyecto.citiodeportivo.dto.AdministradorResponseDTO;
import com.proyecto.citiodeportivo.entities.AdministradorEntity;
import org.springframework.stereotype.Component;

@Component
public class AdministradorMapper {

    public AdministradorEntity toEntity(AdministradorRequestDTO dto) {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setUsuario(dto.getUsuario());
        entity.setRol(dto.getRol());
        entity.setPasswordHash(dto.getPassword());
        return entity;
    }

    public AdministradorResponseDTO toDTO(AdministradorEntity entity) {
        AdministradorResponseDTO dto = new AdministradorResponseDTO();
        dto.setIdAdministrador(entity.getIdAdmin());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setUsuario(entity.getUsuario());
        dto.setRol(entity.getRol());
        return dto;
    }
}
