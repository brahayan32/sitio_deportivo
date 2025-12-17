package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.dto.AdministradorRequestDTO;
import com.proyecto.citiodeportivo.dto.AdministradorResponseDTO;
import com.proyecto.citiodeportivo.entities.AdministradorEntity;
import java.util.List;

public interface AdministradorService {
    List<AdministradorResponseDTO> findAll();
    AdministradorResponseDTO findById(Integer id);
    AdministradorResponseDTO save(AdministradorRequestDTO dto);
    AdministradorResponseDTO update(Integer id, AdministradorRequestDTO dto);
    void delete(Integer id);
}
