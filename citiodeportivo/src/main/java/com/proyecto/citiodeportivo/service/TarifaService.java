package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.dto.TarifaRequestDTO;
import com.proyecto.citiodeportivo.entities.TarifaEntity;
import java.util.List;

public interface TarifaService {
    List<TarifaEntity> findAll();
    TarifaEntity findById(Integer id);
    TarifaEntity save(TarifaEntity tarifa);
    TarifaEntity update(Integer id, TarifaRequestDTO dto);
    void delete(Integer id);

    // Filtros
    List<TarifaEntity> findByTipoServicio(String tipo);
    List<TarifaEntity> findVigentes();
    TarifaEntity findTarifaVigentePorCancha(Integer idCancha);

}
