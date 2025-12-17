package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.TarifaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarifaRepository extends JpaRepository<TarifaEntity, Integer> {

    List<TarifaEntity> findByTipoServicio(String tipoServicio);
    List<TarifaEntity> findByVigente(Boolean vigente);

    // ✅ CORREGIDO: Buscar tarifa vigente por cancha usando subquery
    @Query(
            "SELECT t FROM TarifaEntity t " +
                    "WHERE t.idTarifa IN (" +
                    "  SELECT c.tarifa.idTarifa FROM CanchaEntity c " +
                    "  WHERE c.idCancha = :idCancha" +
                    ") " +
                    "AND t.vigente = true"
    )
    Optional<TarifaEntity> findByCancha_IdCanchaAndVigenteTrue(@Param("idCancha") Integer idCancha);

}