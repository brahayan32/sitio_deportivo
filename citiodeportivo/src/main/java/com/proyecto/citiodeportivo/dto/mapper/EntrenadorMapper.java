package com.proyecto.citiodeportivo.dto.mapper;

import com.proyecto.citiodeportivo.dto.EntrenadorRequestDTO;
import com.proyecto.citiodeportivo.dto.EntrenadorResponseDTO;
import com.proyecto.citiodeportivo.entities.EntrenadorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class EntrenadorMapper {

    private final BCryptPasswordEncoder encoder;

    public EntrenadorEntity toEntity(EntrenadorRequestDTO dto) {
        EntrenadorEntity e = new EntrenadorEntity();
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setEspecialidad(dto.getEspecialidad());
        e.setEmail(dto.getEmail());
        e.setTelefono(dto.getTelefono());

        if (dto.getPassword() != null) {
            e.setPasswordHash(encoder.encode(dto.getPassword()));
        }

        e.setCreadoEn(LocalDateTime.now());
        return e;
    }
    public EntrenadorResponseDTO toDTO(EntrenadorEntity e) {
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setIdEntrenador(e.getIdEntrenador());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setEspecialidad(e.getEspecialidad());
        dto.setEmail(e.getEmail());
        dto.setTelefono(e.getTelefono());
        dto.setCreadoEn(e.getCreadoEn());
        return dto;
    }

    public void updateEntity(EntrenadorEntity e, EntrenadorRequestDTO dto) {
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            e.setPasswordHash(encoder.encode(dto.getPassword()));
        }
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setEspecialidad(dto.getEspecialidad());
        e.setEmail(dto.getEmail());
        e.setTelefono(dto.getTelefono());
    }

}
