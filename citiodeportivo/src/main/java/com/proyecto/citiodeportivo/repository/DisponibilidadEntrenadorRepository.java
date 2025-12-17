package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.DisponibilidadEntrenadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadEntrenadorRepository
        extends JpaRepository<DisponibilidadEntrenadorEntity, Integer> {

    List<DisponibilidadEntrenadorEntity> findByEntrenador_IdEntrenador(Integer idEntrenador);
}
