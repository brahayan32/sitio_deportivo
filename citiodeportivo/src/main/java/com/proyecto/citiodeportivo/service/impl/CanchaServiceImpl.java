package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.dto.CanchaRequestDTO;
import com.proyecto.citiodeportivo.dto.CanchaResponseDTO;
import com.proyecto.citiodeportivo.dto.mapper.CanchaMapper;
import com.proyecto.citiodeportivo.entities.CanchaEntity;
import com.proyecto.citiodeportivo.entities.TarifaEntity;
import com.proyecto.citiodeportivo.entities.enums.TipoCancha;
import com.proyecto.citiodeportivo.repository.CanchaRepository;
import com.proyecto.citiodeportivo.repository.TarifaRepository;
import com.proyecto.citiodeportivo.service.CanchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CanchaServiceImpl implements CanchaService {

    private final CanchaRepository canchaRepository;
    private final CanchaMapper canchaMapper;
    private final TarifaRepository tarifaRepository;

    @Override
    public List<CanchaResponseDTO> findAll() {
        return canchaRepository.findAll()
                .stream()
                .map(canchaMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CanchaResponseDTO findById(Integer id) {
        CanchaEntity cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));
        return canchaMapper.toResponseDTO(cancha);
    }

    @Override
    public CanchaResponseDTO save(CanchaRequestDTO dto) {

        validarDominio(dto);

        CanchaEntity entity = canchaMapper.toEntity(dto);

        if (dto.getIdTarifa() != null) {
            TarifaEntity tarifa = tarifaRepository.findById(dto.getIdTarifa())
                    .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));
            entity.setTarifa(tarifa);
        }

        entity.setUltimaModificacion(LocalDateTime.now());
        return canchaMapper.toResponseDTO(canchaRepository.save(entity));
    }


    @Override
    public CanchaResponseDTO update(Integer id, CanchaRequestDTO dto) {

        CanchaEntity actual = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        actual.setNombre(dto.getNombre());
        actual.setTipo(dto.getTipo());
        actual.setEstado(dto.getEstado());
        actual.setDescripcion(dto.getDescripcion());

        if (dto.getIdTarifa() != null) {
            TarifaEntity tarifa = tarifaRepository.findById(dto.getIdTarifa())
                    .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));
            actual.setTarifa(tarifa);
        }

        actual.setUltimaModificacion(LocalDateTime.now());
        return canchaMapper.toResponseDTO(canchaRepository.save(actual));
    }


    @Override
    public void delete(Integer id) {
        canchaRepository.deleteById(id);
    }

    /* =======================
       VALIDACIONES DE NEGOCIO
       ======================= */

    private void validarDominio(CanchaRequestDTO dto) {

        if (canchaRepository.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("Ya existe una cancha con ese nombre");
        }

        validarLimitePorTipo(dto.getTipo());
    }

    private void validarLimitePorTipo(TipoCancha tipo) {

        long cantidad = canchaRepository.countByTipo(tipo);

        if (cantidad >= 2) {
            throw new RuntimeException(
                    "No se pueden registrar más de 2 canchas del tipo " + tipo
            );
        }
    }
}
