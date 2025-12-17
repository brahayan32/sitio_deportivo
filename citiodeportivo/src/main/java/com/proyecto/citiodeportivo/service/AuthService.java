package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.dto.LoginRequestDTO;
import com.proyecto.citiodeportivo.dto.LoginResponseDTO;
import com.proyecto.citiodeportivo.dto.RegistroRequestDTO;
import com.proyecto.citiodeportivo.dto.RegistroResponseDTO;

public interface AuthService {

    /**
     * Login de usuario existente
     * POST /auth/login
     */
    LoginResponseDTO login(LoginRequestDTO dto);

    /**
     * Registrar nuevo usuario
     * POST /auth/registro
     */
    RegistroResponseDTO registro(RegistroRequestDTO dto);

    /**
     * Verificar si usuario está disponible
     * GET /auth/usuario/{usuario}/disponible
     */
    boolean usuarioDisponible(String usuario);

    /**
     * Verificar si email está disponible
     * GET /auth/email/{email}/disponible
     */
    boolean emailDisponible(String email);
}