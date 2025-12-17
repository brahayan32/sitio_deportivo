package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.TarifaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarifaRepository extends JpaRepository<TarifaEntity, Integer> {

    List<TarifaEntity> findByTipoServicio(String tipoServicio);

    List<TarifaEntity> findByVigente(Boolean vigente);
}
