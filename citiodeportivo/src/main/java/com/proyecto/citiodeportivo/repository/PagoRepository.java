package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {
    List<PagoEntity> findByCliente_IdCliente(Integer idCliente);
}
