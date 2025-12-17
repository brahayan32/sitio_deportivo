package com.proyecto.citiodeportivo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolicitudEntrenamientoRequestDTO {
    private Integer idCliente;
    private Integer idEntrenador; // puede ser null si aún no se asigna
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String estado;
}
