package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.entities.SolicitudEntrenamientoEntity;
import com.proyecto.citiodeportivo.entities.EntrenadorEntity;
import com.proyecto.citiodeportivo.repository.SolicitudEntrenamientoRepository;
import com.proyecto.citiodeportivo.repository.EntrenadorRepository;
import com.proyecto.citiodeportivo.service.SolicitudEntrenamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudEntrenamientoServiceImpl implements SolicitudEntrenamientoService {

    private final SolicitudEntrenamientoRepository repository;
    private final EntrenadorRepository entrenadorRepository;

    @Override
    public List<SolicitudEntrenamientoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<SolicitudEntrenamientoEntity> findByEntrenador(Integer idEntrenador) {
        return repository.findByEntrenador_IdEntrenador(idEntrenador);
    }

    @Override
    public SolicitudEntrenamientoEntity findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    @Override
    public SolicitudEntrenamientoEntity save(SolicitudEntrenamientoEntity s) {
        if (s.getInicio() == null || s.getFin() == null) {
            throw new RuntimeException("Inicio y fin son obligatorios");
        }
        return repository.save(s);
    }

    @Override
    public SolicitudEntrenamientoEntity update(Integer id, SolicitudEntrenamientoEntity s) {
        SolicitudEntrenamientoEntity actual = findById(id);
        actual.setCliente(s.getCliente());
        actual.setInicio(s.getInicio());
        actual.setFin(s.getFin());
        actual.setEstado(s.getEstado());
        return repository.save(actual);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /**
     * ✅ CRÍTICO: Asignar un entrenador a una solicitud
     */
    @Override
    public SolicitudEntrenamientoEntity asignarEntrenador(Integer idSolicitud, Integer idEntrenador) {
        SolicitudEntrenamientoEntity solicitud = findById(idSolicitud);

        // Validar que el entrenador existe
        EntrenadorEntity entrenador = entrenadorRepository.findById(idEntrenador)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con ID: " + idEntrenador));

        // Validar que no hay solapamiento de horarios
        int solapamientos = repository.existeSolapamiento(
                idEntrenador,
                solicitud.getInicio(),
                solicitud.getFin()
        );

        if (solapamientos > 0) {
            throw new RuntimeException("El entrenador tiene conflictos de horario");
        }

        // Asignar entrenador y cambiar estado
        solicitud.setEntrenador(entrenador);
        solicitud.setEstado("ACEPTADA");

        return repository.save(solicitud);
    }

    /**
     * ✅ CRÍTICO: Liberar un entrenador de una solicitud
     */
    @Override
    public SolicitudEntrenamientoEntity liberarEntrenador(Integer idSolicitud) {
        SolicitudEntrenamientoEntity solicitud = findById(idSolicitud);
        solicitud.setEntrenador(null);
        solicitud.setEstado("PENDIENTE");
        return repository.save(solicitud);
    }

}