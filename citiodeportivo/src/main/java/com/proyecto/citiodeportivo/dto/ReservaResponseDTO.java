package com.proyecto.citiodeportivo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaResponseDTO {

    private Integer idReserva;
    private Integer clienteId;
    private Integer canchaId;
    private Integer tarifaId;

    private LocalDateTime inicio;
    private LocalDateTime fin;
    private Boolean incluirEntrenador;
    private String estado;
    private Double totalPagar;
    private LocalDateTime creadoEn;

    private Integer creadoPorAdminId; // opcional
}
