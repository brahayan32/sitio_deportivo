package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.dto.CanchaRequestDTO;
import com.proyecto.citiodeportivo.dto.CanchaResponseDTO;

import java.util.List;

public interface CanchaService {

    List<CanchaResponseDTO> findAll();
    CanchaResponseDTO findById(Integer id);
    CanchaResponseDTO save(CanchaRequestDTO dto);
    CanchaResponseDTO update(Integer id, CanchaRequestDTO dto);
    void delete(Integer id);
}
