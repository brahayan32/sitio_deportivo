package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.dto.AdministradorRequestDTO;
import com.proyecto.citiodeportivo.dto.AdministradorResponseDTO;
import com.proyecto.citiodeportivo.dto.mapper.AdministradorMapper;
import com.proyecto.citiodeportivo.entities.AdministradorEntity;
import com.proyecto.citiodeportivo.repository.AdministradorRepository;
import com.proyecto.citiodeportivo.service.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository adminRepository;
    private final AdministradorMapper mapper;
    private final PasswordEncoder encoder;


    @Override
    public List<AdministradorResponseDTO> findAll() {
        return adminRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public AdministradorResponseDTO findById(Integer id) {
        AdministradorEntity entity = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        return mapper.toDTO(entity);
    }

    @Override
    public AdministradorResponseDTO save(AdministradorRequestDTO dto) {
        AdministradorEntity entity = mapper.toEntity(dto);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPasswordHash(encoder.encode(dto.getPassword()));
        }

        return mapper.toDTO(adminRepository.save(entity));
    }

    @Override
    public AdministradorResponseDTO update(Integer id, AdministradorRequestDTO dto) {
        AdministradorEntity entity = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setUsuario(dto.getUsuario());
        entity.setRol(dto.getRol());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPasswordHash(encoder.encode(dto.getPassword()));
        }

        return mapper.toDTO(adminRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        adminRepository.deleteById(id);
    }
}
