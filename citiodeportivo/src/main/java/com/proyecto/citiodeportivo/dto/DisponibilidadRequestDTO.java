package com.proyecto.citiodeportivo.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class DisponibilidadRequestDTO {
    private Integer idEntrenador;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
