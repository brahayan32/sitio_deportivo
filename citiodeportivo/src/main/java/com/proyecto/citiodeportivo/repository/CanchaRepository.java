package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.CanchaEntity;
import com.proyecto.citiodeportivo.entities.enums.TipoCancha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanchaRepository extends JpaRepository<CanchaEntity, Integer> {

    long countByTipo(TipoCancha tipo);

    boolean existsByNombre(String nombre);
}
