package com.proyecto.citiodeportivo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EntrenadorResponseDTO {
    private Integer idEntrenador;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;
    private String telefono;
    private LocalDateTime creadoEn;
}

