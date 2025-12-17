package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.CanchaEntity;
import com.proyecto.citiodeportivo.entities.TarifaEntity;
import com.proyecto.citiodeportivo.entities.enums.TipoCancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CanchaRepository extends JpaRepository<CanchaEntity, Integer> {

    long countByTipo(TipoCancha tipo);

    boolean existsByNombre(String nombre);

    @Query(
            "SELECT c.tarifa " +
                    "FROM CanchaEntity c " +
                    "WHERE c.idCancha = :idCancha " +
                    "AND c.tarifa.vigente = true"
    )
    Optional<TarifaEntity> findTarifaVigentePorCancha(@Param("idCancha") Integer idCancha);
}
