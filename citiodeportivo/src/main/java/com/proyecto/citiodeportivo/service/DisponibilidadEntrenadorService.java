package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.entities.DisponibilidadEntrenadorEntity;
import java.util.List;

public interface DisponibilidadEntrenadorService {
    List<DisponibilidadEntrenadorEntity> findAll();
    DisponibilidadEntrenadorEntity findById(Integer id);
    DisponibilidadEntrenadorEntity save(DisponibilidadEntrenadorEntity d);
    DisponibilidadEntrenadorEntity update(Integer id, DisponibilidadEntrenadorEntity d);
    void delete(Integer id);

    List<DisponibilidadEntrenadorEntity> findByEntrenador(Integer idEntrenador);

}
