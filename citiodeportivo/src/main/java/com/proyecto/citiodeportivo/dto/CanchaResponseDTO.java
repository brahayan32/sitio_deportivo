package com.proyecto.citiodeportivo.dto;

import com.proyecto.citiodeportivo.entities.enums.EstadoCancha;
import com.proyecto.citiodeportivo.entities.enums.TipoCancha;
import lombok.Data;

@Data
public class CanchaResponseDTO {

    private Integer idCancha;
    private String nombre;
    private TipoCancha tipo;
    private EstadoCancha estado;
    private String descripcion;
}
