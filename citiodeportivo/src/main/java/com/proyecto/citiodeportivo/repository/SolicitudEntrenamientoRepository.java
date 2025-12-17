package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.SolicitudEntrenamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface SolicitudEntrenamientoRepository extends JpaRepository<SolicitudEntrenamientoEntity, Integer> {

    // ✅ Verificar solapamiento de horarios
    @Query("""
    SELECT COUNT(s) FROM SolicitudEntrenamientoEntity s
    WHERE s.entrenador.idEntrenador = :idEntrenador
      AND (:inicio < s.fin AND :fin > s.inicio)
""")
    int existeSolapamiento(@Param("idEntrenador") Integer idEntrenador,
                           @Param("inicio") LocalDateTime inicio,
                           @Param("fin") LocalDateTime fin);

    // ✅ NUEVO: Obtener solicitudes por entrenador (CRÍTICO)
    List<SolicitudEntrenamientoEntity> findByEntrenador_IdEntrenador(Integer idEntrenador);

}