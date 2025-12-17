package com.proyecto.citiodeportivo.dto;

import lombok.Data;

/**
 * DTO para solicitar registro de nuevo usuario
 * POST /auth/registro
 */
@Data
public class RegistroRequestDTO {

    private String nombre;              // Obligatorio
    private String usuario;             // Obligatorio solo para ADMIN
    private String email;               // Obligatorio
    private String password;            // Obligatorio, min 6 caracteres
    private String rol;                 // CLIENTE, ENTRENADOR, ADMIN
    private String telefono;            // Obligatorio
    private String documento;           // Opcional (cédula o pasaporte)

}