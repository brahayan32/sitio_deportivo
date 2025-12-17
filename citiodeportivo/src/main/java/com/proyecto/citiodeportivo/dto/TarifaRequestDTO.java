package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
public class TarifaRequestDTO {


    private String tipoServicio;  // futbol6, padel, entrenamiento
    private Double precioHora;
    private Boolean vigente;
    private Integer idAdmin; // creadoPorAdmin

}
