package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.dto.EntrenadorRequestDTO;
import com.proyecto.citiodeportivo.dto.EntrenadorResponseDTO;
import com.proyecto.citiodeportivo.dto.mapper.EntrenadorMapper;
import com.proyecto.citiodeportivo.entities.EntrenadorEntity;
import com.proyecto.citiodeportivo.repository.EntrenadorRepository;
import com.proyecto.citiodeportivo.service.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final EntrenadorMapper mapper;

    @Override
    public List<EntrenadorResponseDTO> findAll() {
        return entrenadorRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public EntrenadorResponseDTO findById(Integer id) {
        return entrenadorRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
    }

    @Override
    public EntrenadorResponseDTO save(EntrenadorRequestDTO dto) {
        EntrenadorEntity e = mapper.toEntity(dto);
        return mapper.toDTO(entrenadorRepository.save(e));
    }

    @Override
    public EntrenadorResponseDTO update(Integer id, EntrenadorRequestDTO dto) {
        EntrenadorEntity e = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        mapper.updateEntity(e, dto);

        return mapper.toDTO(entrenadorRepository.save(e));
    }

    @Override
    public void delete(Integer id) {
        entrenadorRepository.deleteById(id);
    }

    @Override
    public EntrenadorResponseDTO findByEmail(String email) {
        return entrenadorRepository.findByEmail(email)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public EntrenadorEntity findEntityByEmail(String email) {
        return entrenadorRepository.findByEmail(email).orElse(null);
    }

}
