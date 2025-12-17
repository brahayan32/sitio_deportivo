package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    // Ya tienes esto
    Optional<ClienteEntity> findByEmail(String email);

    // ✅ AGREGAR ESTOS
    Optional<ClienteEntity> findByDocumento(String documento);
}