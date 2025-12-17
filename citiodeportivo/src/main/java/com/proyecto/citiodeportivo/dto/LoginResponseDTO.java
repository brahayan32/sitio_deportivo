package com.proyecto.citiodeportivo.dto;

import lombok.Data;

/**
 * DTO para respuesta de login
 * Incluye token JWT y datos del usuario
 */
@Data
public class LoginResponseDTO {

    private String token;           // Token JWT
    private String rol;             // ADMIN, CLIENTE, ENTRENADOR
    private String nombre;          // Nombre completo
    private Integer idUsuario;      // ID del usuario
    private Integer idCliente;      // ID (solo si es CLIENTE)
    private Integer idEntrenador;   // ID (solo si es ENTRENADOR)

}