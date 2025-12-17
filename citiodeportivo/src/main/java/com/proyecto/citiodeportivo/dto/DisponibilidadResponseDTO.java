package com.proyecto.citiodeportivo.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class DisponibilidadResponseDTO {
    private Integer idDisponibilidad;
    private Integer idEntrenador;
    private String entrenadorNombre;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
