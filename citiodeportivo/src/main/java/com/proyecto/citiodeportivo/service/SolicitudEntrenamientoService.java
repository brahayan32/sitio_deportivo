package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.entities.SolicitudEntrenamientoEntity;
import java.util.List;

public interface SolicitudEntrenamientoService {
    List<SolicitudEntrenamientoEntity> findAll();
    SolicitudEntrenamientoEntity findById(Integer id);
    SolicitudEntrenamientoEntity save(SolicitudEntrenamientoEntity s);
    SolicitudEntrenamientoEntity update(Integer id, SolicitudEntrenamientoEntity s);
    void delete(Integer id);

    // ✅ AGREGAR ESTOS DOS
    List<SolicitudEntrenamientoEntity> findByEntrenador(Integer idEntrenador);
    SolicitudEntrenamientoEntity asignarEntrenador(Integer idSolicitud, Integer idEntrenador);
    SolicitudEntrenamientoEntity liberarEntrenador(Integer idSolicitud);
}