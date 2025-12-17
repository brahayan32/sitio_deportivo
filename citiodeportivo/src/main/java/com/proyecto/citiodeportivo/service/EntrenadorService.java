package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.dto.EntrenadorRequestDTO;
import com.proyecto.citiodeportivo.dto.EntrenadorResponseDTO;
import com.proyecto.citiodeportivo.entities.EntrenadorEntity;

import java.util.List;

public interface EntrenadorService {

    List<EntrenadorResponseDTO> findAll();

    EntrenadorResponseDTO findById(Integer id);

    EntrenadorResponseDTO save(EntrenadorRequestDTO dto);

    EntrenadorResponseDTO update(Integer id, EntrenadorRequestDTO dto);

    void delete(Integer id);

    EntrenadorEntity findEntityByEmail(String email);

    EntrenadorResponseDTO findByEmail(String email);
}
