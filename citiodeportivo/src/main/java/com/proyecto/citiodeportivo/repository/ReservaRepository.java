package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Integer> {

    @Query("""
        SELECT COUNT(r) 
        FROM ReservaEntity r 
        WHERE r.cancha.idCancha = :idCancha
        AND (
            (r.inicio < :fin AND r.fin > :inicio)
        )
    """)
    int existeSolapamiento(
            @Param("idCancha") Integer idCancha,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}

