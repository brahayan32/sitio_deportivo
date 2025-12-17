package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
public class AdministradorResponseDTO {
    private Integer idAdministrador;
    private String nombre;
    private String apellido;
    private String usuario;
    private String rol;
}
