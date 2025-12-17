package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.dto.ReporteRequestDTO;
import com.proyecto.citiodeportivo.dto.ReporteResponseDTO;
import com.proyecto.citiodeportivo.entities.ReporteEntity;
import com.proyecto.citiodeportivo.repository.AdministradorRepository;
import com.proyecto.citiodeportivo.repository.ReporteRepository;
import com.proyecto.citiodeportivo.repository.ReservaRepository;
import com.proyecto.citiodeportivo.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository reporteRepository;
    private final AdministradorRepository adminRepo;
    private final ReservaRepository reservaRepo;
    private final com.proyecto.citiodeportivo.dto.mapper.ReporteMapper mapper;

    @Override
    public List<ReporteResponseDTO> findAll() {
        return reporteRepository.findAll()
                .stream().map(mapper::toDTO).toList();
    }

    @Override
    public ReporteResponseDTO findById(Integer id) {
        return mapper.toDTO(
                reporteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Reporte no encontrado"))
        );
    }

    @Override
    public ReporteResponseDTO save(ReporteRequestDTO dto) {

        ReporteEntity r = mapper.toEntity(dto);

        r.setFechaGenerado(LocalDateTime.now());

        r.setAdministrador(adminRepo.findById(dto.getAdministradorId())
                .orElseThrow(() -> new RuntimeException("Admin no encontrado")));

        r.setReserva(reservaRepo.findById(dto.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada")));

        return mapper.toDTO(reporteRepository.save(r));
    }

    @Override
    public void delete(Integer id) {
        reporteRepository.deleteById(id);
    }

    @Override
    public List<ReporteResponseDTO> filtrarPorFechas(LocalDateTime inicio, LocalDateTime fin) {
        return reporteRepository.findByFechaGeneradoBetween(inicio, fin)
                .stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<ReporteResponseDTO> filtrarPorCancha(Integer idCancha) {
        return reporteRepository.findByReserva_Cancha_IdCancha(idCancha)
                .stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<ReporteResponseDTO> filtrarPorCliente(Integer idCliente) {
        return reporteRepository.findByReserva_Cliente_IdCliente(idCliente)
                .stream().map(mapper::toDTO).toList();
    }
}

