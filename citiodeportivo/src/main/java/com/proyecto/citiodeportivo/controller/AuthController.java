package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.LoginRequestDTO;
import com.proyecto.citiodeportivo.dto.LoginResponseDTO;
import com.proyecto.citiodeportivo.dto.RegistroRequestDTO;
import com.proyecto.citiodeportivo.dto.RegistroResponseDTO;
import com.proyecto.citiodeportivo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Login de usuario
     * POST /auth/login
     *
     * Body:
     * {
     *   "identifier": "usuario o email",
     *   "password": "contraseña"
     * }
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        try {
            LoginResponseDTO response = authService.login(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDTO());
        }
    }

    /**
     * Registrar nuevo usuario
     * POST /auth/registro
     *
     * Body:
     * {
     *   "nombre": "Juan Pérez",
     *   "usuario": "juanperez",  (solo para ADMIN)
     *   "email": "juan@example.com",
     *   "password": "password123",
     *   "rol": "CLIENTE",
     *   "telefono": "+57 3001234567",
     *   "documento": "12345678"
     * }
     */
    @PostMapping("/registro")
    public ResponseEntity<RegistroResponseDTO> registro(@RequestBody RegistroRequestDTO dto) {
        try {
            RegistroResponseDTO response = authService.registro(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            RegistroResponseDTO error = new RegistroResponseDTO();
            error.setSuccess(false);
            error.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            RegistroResponseDTO error = new RegistroResponseDTO();
            error.setSuccess(false);
            error.setMessage("Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * Validar si usuario está disponible
     * GET /auth/usuario/{usuario}/disponible
     *
     * Response: true (disponible) o false (no disponible)
     */
    @GetMapping("/usuario/{usuario}/disponible")
    public ResponseEntity<Boolean> verificarUsuario(@PathVariable String usuario) {
        boolean disponible = authService.usuarioDisponible(usuario);
        return ResponseEntity.ok(disponible);
    }

    /**
     * Validar si email está disponible
     * GET /auth/email/{email}/disponible
     *
     * Response: true (disponible) o false (no disponible)
     */
    @GetMapping("/email/{email}/disponible")
    public ResponseEntity<Boolean> verificarEmail(@PathVariable String email) {
        boolean disponible = authService.emailDisponible(email);
        return ResponseEntity.ok(disponible);
    }
}