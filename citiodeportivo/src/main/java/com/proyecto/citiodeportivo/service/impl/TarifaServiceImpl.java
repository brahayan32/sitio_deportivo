package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.dto.TarifaRequestDTO;
import com.proyecto.citiodeportivo.entities.TarifaEntity;
import com.proyecto.citiodeportivo.repository.AdministradorRepository;
import com.proyecto.citiodeportivo.repository.CanchaRepository;
import com.proyecto.citiodeportivo.repository.TarifaRepository;
import com.proyecto.citiodeportivo.service.TarifaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService {

    private final TarifaRepository tarifaRepository;
    private final AdministradorRepository adminRepo;

    @Override
    public TarifaEntity findTarifaVigentePorCancha(Integer idCancha) {
        return tarifaRepository
                .findByCancha_IdCanchaAndVigenteTrue(idCancha)
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));
    }


    @Override
    public List<TarifaEntity> findAll() {
        return tarifaRepository.findAll();
    }

    @Override
    public TarifaEntity findById(Integer id) {
        return tarifaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));
    }

    @Override
    public TarifaEntity save(TarifaEntity tarifa) {

        if (tarifa.getPrecioHora() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if (tarifa.getTipoServicio() == null || tarifa.getTipoServicio().isBlank()) {
            throw new RuntimeException("El tipo de servicio es obligatorio");
        }

        return tarifaRepository.save(tarifa);
    }

    @Override
    public TarifaEntity update(Integer id, TarifaRequestDTO dto) {

        TarifaEntity actual = findById(id);

        actual.setTipoServicio(dto.getTipoServicio());
        actual.setPrecioHora(dto.getPrecioHora());
        actual.setVigente(dto.getVigente());

        if (dto.getIdAdmin() != null) {
            actual.setCreadoPorAdmin(
                    adminRepo.findById(dto.getIdAdmin())
                            .orElseThrow(() -> new RuntimeException("Admin no encontrado"))
            );
        }

        return tarifaRepository.save(actual);
    }

    @Override
    public void delete(Integer id) {
        tarifaRepository.deleteById(id);
    }

    @Override
    public List<TarifaEntity> findByTipoServicio(String tipo) {
        return tarifaRepository.findByTipoServicio(tipo);
    }

    @Override
    public List<TarifaEntity> findVigentes() {
        return tarifaRepository.findByVigente(true);
    }
}
