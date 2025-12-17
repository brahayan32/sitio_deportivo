package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.dto.CanchaRequestDTO;
import com.proyecto.citiodeportivo.dto.CanchaResponseDTO;
import com.proyecto.citiodeportivo.dto.mapper.CanchaMapper;
import com.proyecto.citiodeportivo.entities.CanchaEntity;
import com.proyecto.citiodeportivo.entities.enums.TipoCancha;
import com.proyecto.citiodeportivo.repository.CanchaRepository;
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
        entity.setUltimaModificacion(LocalDateTime.now());

        return canchaMapper.toResponseDTO(canchaRepository.save(entity));
    }

    @Override
    public CanchaResponseDTO update(Integer id, CanchaRequestDTO dto) {

        CanchaEntity actual = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        // si cambia el tipo, validar nuevamente el límite
        if (!actual.getTipo().equals(dto.getTipo())) {
            validarLimitePorTipo(dto.getTipo());
        }

        actual.setNombre(dto.getNombre());
        actual.setTipo(dto.getTipo());
        actual.setEstado(dto.getEstado());
        actual.setDescripcion(dto.getDescripcion());
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
