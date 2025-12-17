package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
class UsuarioRegistroDTO {

    private Integer idUsuario;
    private String nombre;
    private String email;
    private String rol;

}