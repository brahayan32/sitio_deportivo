package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.entities.EntrenadorEntity;
import com.proyecto.citiodeportivo.entities.SolicitudEntrenamientoEntity;
import com.proyecto.citiodeportivo.repository.EntrenadorRepository;
import com.proyecto.citiodeportivo.repository.SolicitudEntrenamientoRepository;
import com.proyecto.citiodeportivo.service.SolicitudEntrenamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudEntrenamientoServiceImpl implements SolicitudEntrenamientoService {

    private final SolicitudEntrenamientoRepository repo;
    private final EntrenadorRepository entrenadorRepository;

    @Override
    public List<SolicitudEntrenamientoEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public SolicitudEntrenamientoEntity findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    @Override
    public SolicitudEntrenamientoEntity save(SolicitudEntrenamientoEntity s) {

        if (s.getEntrenador() != null &&
                repo.existeSolapamiento(s.getEntrenador().getIdEntrenador(),
                        s.getInicio(), s.getFin()) > 0) {
            throw new RuntimeException("El entrenador ya tiene entrenamiento en ese horario.");
        }

        return repo.save(s);
    }

    @Override
    public SolicitudEntrenamientoEntity update(Integer id, SolicitudEntrenamientoEntity d) {
        SolicitudEntrenamientoEntity actual = findById(id);

        if (d.getEntrenador() != null &&
                repo.existeSolapamiento(d.getEntrenador().getIdEntrenador(),
                        d.getInicio(), d.getFin()) > 0) {
            throw new RuntimeException("El entrenador ya tiene entrenamiento en ese horario.");
        }

        actual.setInicio(d.getInicio());
        actual.setFin(d.getFin());
        actual.setEstado(d.getEstado());
        actual.setEntrenador(d.getEntrenador());

        return repo.save(actual);
    }


    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public SolicitudEntrenamientoEntity asignarEntrenador(Integer idSolicitud, Integer idEntrenador) {
        SolicitudEntrenamientoEntity s = findById(idSolicitud);

        EntrenadorEntity e = entrenadorRepository.findById(idEntrenador)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        if (repo.existeSolapamiento(idEntrenador, s.getInicio(), s.getFin()) > 0) {
            throw new RuntimeException("Entrenador ocupado en ese horario.");
        }

        s.setEntrenador(e);
        s.setEstado("ASIGNADO");
        return repo.save(s);
    }


    @Override
    public SolicitudEntrenamientoEntity liberarEntrenador(Integer idSolicitud) {
        SolicitudEntrenamientoEntity s = findById(idSolicitud);
        s.setEntrenador(null);
        s.setEstado("PENDIENTE");
        return repo.save(s);
    }

}
