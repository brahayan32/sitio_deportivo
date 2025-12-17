package com.proyecto.citiodeportivo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SolicitudEntrenamientoResponseDTO {
    private Integer idSolicitud;
    private Integer idCliente;
    private Integer idEntrenador;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String estado;
    private LocalDateTime creadoEn;
}
