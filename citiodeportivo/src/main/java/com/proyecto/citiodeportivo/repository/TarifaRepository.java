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

    @Query("""
SELECT c.tarifa
FROM CanchaEntity c
WHERE c.idCancha = :idCancha
AND c.tarifa.vigente = true
""")
    Optional<TarifaEntity> findTarifaVigentePorCancha(@Param("idCancha") Integer idCancha);


}
