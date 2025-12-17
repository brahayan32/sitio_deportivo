package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String password; // si deseas pedirla aquí
}
