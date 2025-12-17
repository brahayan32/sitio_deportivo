package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.SolicitudEntrenamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface SolicitudEntrenamientoRepository extends JpaRepository<SolicitudEntrenamientoEntity, Integer> {
    @Query("""
    SELECT COUNT(s) FROM SolicitudEntrenamientoEntity s
    WHERE s.entrenador.idEntrenador = :idEntrenador
      AND (:inicio < s.fin AND :fin > s.inicio)
""")
    int existeSolapamiento(Integer idEntrenador, LocalDateTime inicio, LocalDateTime fin);

}
