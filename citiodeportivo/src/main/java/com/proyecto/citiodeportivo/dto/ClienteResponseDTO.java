package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
