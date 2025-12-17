package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
public class AdministradorRequestDTO {
    private String nombre;
    private String apellido;
    private String usuario;
    private String rol;
    private String password;
}
