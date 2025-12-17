package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Integer> {

    // Ya tienes esto
    Optional<AdministradorEntity> findByUsuario(String usuario);

    // ✅ AGREGAR ESTOS
    Optional<AdministradorEntity> findByEmail(String email);
}