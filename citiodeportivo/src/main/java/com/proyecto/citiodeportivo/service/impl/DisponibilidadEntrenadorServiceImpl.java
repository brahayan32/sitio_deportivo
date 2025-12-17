package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.entities.DisponibilidadEntrenadorEntity;
import com.proyecto.citiodeportivo.repository.DisponibilidadEntrenadorRepository;
import com.proyecto.citiodeportivo.service.DisponibilidadEntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DisponibilidadEntrenadorServiceImpl implements DisponibilidadEntrenadorService {

    private final DisponibilidadEntrenadorRepository repo;

    @Override
    public List<DisponibilidadEntrenadorEntity> findByEntrenador(Integer idEntrenador) {
        return repo.findByEntrenador_IdEntrenador(idEntrenador);
    }

    @Override
    public List<DisponibilidadEntrenadorEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public DisponibilidadEntrenadorEntity findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada"));
    }

    @Override
    public DisponibilidadEntrenadorEntity save(DisponibilidadEntrenadorEntity d) {
        return repo.save(d);
    }

    @Override
    public DisponibilidadEntrenadorEntity update(Integer id, DisponibilidadEntrenadorEntity data) {
        DisponibilidadEntrenadorEntity actual = findById(id);

        actual.setDiaSemana(data.getDiaSemana());
        actual.setHoraInicio(data.getHoraInicio());
        actual.setHoraFin(data.getHoraFin());
        actual.setEntrenador(data.getEntrenador());

        return repo.save(actual);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

