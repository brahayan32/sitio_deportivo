package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.dto.ReporteRequestDTO;
import com.proyecto.citiodeportivo.dto.ReporteResponseDTO;
import com.proyecto.citiodeportivo.entities.ReporteEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ReporteService {

    List<ReporteResponseDTO> findAll();
    ReporteResponseDTO findById(Integer id);
    ReporteResponseDTO save(ReporteRequestDTO dto);
    void delete(Integer id);

    List<ReporteResponseDTO> filtrarPorFechas(LocalDateTime inicio, LocalDateTime fin);
    List<ReporteResponseDTO> filtrarPorCancha(Integer idCancha);
    List<ReporteResponseDTO> filtrarPorCliente(Integer idCliente);
}


