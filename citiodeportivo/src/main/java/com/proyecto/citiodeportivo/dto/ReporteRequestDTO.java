package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
public class ReporteRequestDTO {
    private Integer administradorId;
    private Integer reservaId;
    private String tipoReporte;
    private String descripcion;
}
