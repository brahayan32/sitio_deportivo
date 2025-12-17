package com.proyecto.citiodeportivo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReporteResponseDTO {
    private Integer idReporte;
    private Integer administradorId;
    private Integer reservaId;
    private LocalDateTime fechaGenerado;
    private String tipoReporte;
    private String descripcion;
}
