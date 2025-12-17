package com.proyecto.citiodeportivo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TarifaResponseDTO {
    private Integer idTarifa;
    private String tipoServicio;
    private Double precioHora;
    private Boolean vigente;
    private LocalDateTime creadoEn;
    private Integer idAdmin;
}
