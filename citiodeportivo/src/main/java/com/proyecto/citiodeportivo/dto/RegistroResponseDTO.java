
package com.proyecto.citiodeportivo.dto;

import lombok.Data;

/**
 * DTO para respuesta de registro
 */
@Data
public class RegistroResponseDTO {

    private boolean success;
    private String message;
    private UsuarioRegistroDTO usuario;

}
