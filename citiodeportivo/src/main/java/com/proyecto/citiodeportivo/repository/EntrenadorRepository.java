package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.EntrenadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntrenadorRepository extends JpaRepository<EntrenadorEntity, Integer> {

    // Ya tienes esto
    Optional<EntrenadorEntity> findByEmail(String email);

    // ✅ AGREGAR ESTOS
    Optional<EntrenadorEntity> findByDocumento(String documento);
}