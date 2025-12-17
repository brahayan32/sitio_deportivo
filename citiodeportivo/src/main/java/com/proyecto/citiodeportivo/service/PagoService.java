package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.entities.PagoEntity;
import java.util.List;

public interface PagoService {
    List<PagoEntity> findAll();
    PagoEntity findById(Integer id);
    PagoEntity save(PagoEntity pago);
    PagoEntity update(Integer id, PagoEntity pago);
    void delete(Integer id);

    List<PagoEntity> findByCliente(Integer idCliente);
}
