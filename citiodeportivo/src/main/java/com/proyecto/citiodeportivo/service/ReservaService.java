package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.entities.ReservaEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaService {

    List<ReservaEntity> findAll();
    ReservaEntity findById(Integer id);
    ReservaEntity save(ReservaEntity reserva);
    ReservaEntity update(Integer id, ReservaEntity reserva);
    void delete(Integer id);

    // Validación de solapamiento
    boolean existeSolapamiento(Integer idCancha, LocalDateTime inicio, LocalDateTime fin);
}
