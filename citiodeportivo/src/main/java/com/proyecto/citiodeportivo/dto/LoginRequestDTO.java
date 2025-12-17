package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String identifier; // puede ser email o usuario
    private String password;
}
