package com.proyecto.citiodeportivo.repository;

import com.proyecto.citiodeportivo.entities.ReporteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReporteRepository extends JpaRepository<ReporteEntity, Integer> {

    // Filtro por fecha
    List<ReporteEntity> findByFechaGeneradoBetween(LocalDateTime inicio, LocalDateTime fin);

    // Filtro por cancha (desde Reserva → Cancha)
    List<ReporteEntity> findByReserva_Cancha_IdCancha(Integer idCancha);

    // Filtro por cliente (desde Reserva → Cliente)
    List<ReporteEntity> findByReserva_Cliente_IdCliente(Integer idCliente);
}

