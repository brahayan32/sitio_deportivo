package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.dto.LoginRequestDTO;
import com.proyecto.citiodeportivo.dto.LoginResponseDTO;
import com.proyecto.citiodeportivo.dto.RegistroRequestDTO;
import com.proyecto.citiodeportivo.dto.RegistroResponseDTO;
import com.proyecto.citiodeportivo.entities.AdministradorEntity;
import com.proyecto.citiodeportivo.entities.ClienteEntity;
import com.proyecto.citiodeportivo.entities.EntrenadorEntity;
import com.proyecto.citiodeportivo.repository.AdministradorRepository;
import com.proyecto.citiodeportivo.repository.ClienteRepository;
import com.proyecto.citiodeportivo.repository.EntrenadorRepository;
import com.proyecto.citiodeportivo.security.JwtUtil;
import com.proyecto.citiodeportivo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdministradorRepository adminRepo;
    private final ClienteRepository clienteRepo;
    private final EntrenadorRepository entrenadorRepo;
    private final JwtUtil jwt;
    private final PasswordEncoder encoder;

    /**
     * Login de usuario existente
     * POST /auth/login
     */
    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {

        String identifier = dto.getIdentifier(); // puede ser usuario o email
        String password = dto.getPassword();

        // Buscar en administradores
        var admin = adminRepo.findByUsuario(identifier).orElse(null);
        if (admin != null) {
            if (!encoder.matches(password, admin.getPasswordHash())) {
                throw new RuntimeException("Contraseña incorrecta");
            }

            return buildLoginResponse(
                    jwt.generateToken(admin.getUsuario(), "ADMIN"),
                    "ADMIN",
                    admin.getNombre(),
                    admin.getIdAdmin()
            );
        }

        // Buscar en clientes por email
        var cliente = clienteRepo.findByEmail(identifier).orElse(null);
        if (cliente != null) {
            if (!encoder.matches(password, cliente.getPasswordHash())) {
                throw new RuntimeException("Contraseña incorrecta");
            }

            return buildLoginResponse(
                    jwt.generateToken(cliente.getEmail(), "CLIENTE"),
                    "CLIENTE",
                    cliente.getNombre(),
                    cliente.getIdCliente()
            );
        }

        // Buscar en entrenadores por email
        var entrenador = entrenadorRepo.findByEmail(identifier).orElse(null);
        if (entrenador != null) {
            if (!encoder.matches(password, entrenador.getPasswordHash())) {
                throw new RuntimeException("Contraseña incorrecta");
            }

            return buildLoginResponse(
                    jwt.generateToken(entrenador.getEmail(), "ENTRENADOR"),
                    "ENTRENADOR",
                    entrenador.getNombre(),
                    entrenador.getIdEntrenador()
            );
        }

        throw new RuntimeException("Usuario no encontrado");
    }

    /**
     * Registrar nuevo usuario
     * POST /auth/registro
     */
    @Override
    public RegistroResponseDTO registro(RegistroRequestDTO dto) {
        // Validaciones
        validarRegistro(dto);

        // Verificar usuario único
        if (usuarioExiste(dto.getUsuario())) {
            throw new IllegalArgumentException("El usuario ya está registrado");
        }

        // Verificar email único
        if (emailExiste(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        RegistroResponseDTO response = new RegistroResponseDTO();
        String passwordHash = encoder.encode(dto.getPassword());
        LocalDateTime ahora = LocalDateTime.now();

        try {
            switch (dto.getRol()) {
                case "CLIENTE":
                    registrarCliente(dto, passwordHash, ahora);
                    break;
                case "ENTRENADOR":
                    registrarEntrenador(dto, passwordHash, ahora);
                    break;
                case "ADMIN":
                    // Solo permitir si hay validación extra
                    registrarAdmin(dto, passwordHash, ahora);
                    break;
                default:
                    throw new IllegalArgumentException("Rol inválido");
            }

            response.setSuccess(true);
            response.setMessage("Usuario registrado exitosamente. Por favor inicia sesión.");
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar usuario: " + e.getMessage());
        }
    }

    /**
     * Registrar nuevo cliente
     */
    private void registrarCliente(RegistroRequestDTO dto, String passwordHash, LocalDateTime ahora) {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setPasswordHash(passwordHash);
        cliente.setTelefono(dto.getTelefono());
        cliente.setCreadoEn(ahora);

        clienteRepo.save(cliente);
    }

    /**
     * Registrar nuevo entrenador
     */
    private void registrarEntrenador(RegistroRequestDTO dto, String passwordHash, LocalDateTime ahora) {
        EntrenadorEntity entrenador = new EntrenadorEntity();
        entrenador.setNombre(dto.getNombre());
        entrenador.setEmail(dto.getEmail());
        entrenador.setPasswordHash(passwordHash);
        entrenador.setTelefono(dto.getTelefono());
        entrenador.setCreadoEn(ahora);
        entrenador.setEspecialidad("General"); // Default

        entrenadorRepo.save(entrenador);
    }

    /**
     * Registrar nuevo administrador
     * IMPORTANTE: Solo permitir con validaciones especiales
     */
    private void registrarAdmin(RegistroRequestDTO dto, String passwordHash, LocalDateTime ahora) {
        AdministradorEntity admin = new AdministradorEntity();
        admin.setNombre(dto.getNombre());
        admin.setUsuario(dto.getUsuario());
        admin.setPasswordHash(passwordHash);
        admin.setRol("ADMIN");
        admin.setCreadoEn(ahora);

        adminRepo.save(admin);
    }

    /**
     * Verificar si usuario está disponible
     */
    @Override
    public boolean usuarioDisponible(String usuario) {
        return !usuarioExiste(usuario);
    }

    /**
     * Verificar si email está disponible
     */
    @Override
    public boolean emailDisponible(String email) {
        return !emailExiste(email);
    }

    /**
     * Validar formulario de registro
     */
    private void validarRegistro(RegistroRequestDTO dto) {
        // Validar nombre
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (dto.getNombre().length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }

        // Validar usuario (solo para ADMIN)
        if ("ADMIN".equals(dto.getRol())) {
            if (dto.getUsuario() == null || dto.getUsuario().length() < 4) {
                throw new IllegalArgumentException("El usuario debe tener mínimo 4 caracteres");
            }
        }

        // Validar email
        if (dto.getEmail() == null || !esEmailValido(dto.getEmail())) {
            throw new IllegalArgumentException("El email no es válido");
        }

        // Validar contraseña
        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 6 caracteres");
        }

        // Validar rol
        if (!esRolValido(dto.getRol())) {
            throw new IllegalArgumentException("Rol inválido. Debe ser: CLIENTE, ENTRENADOR o ADMIN");
        }

        // Validar teléfono
        if (dto.getTelefono() == null || dto.getTelefono().length() < 7) {
            throw new IllegalArgumentException("El teléfono no es válido");
        }
    }

    /**
     * Verificar si usuario existe en alguna tabla
     */
    private boolean usuarioExiste(String usuario) {
        return adminRepo.findByUsuario(usuario).isPresent();
    }

    /**
     * Verificar si email existe
     */
    private boolean emailExiste(String email) {
        return clienteRepo.findByEmail(email).isPresent() ||
                entrenadorRepo.findByEmail(email).isPresent() ||
                adminRepo.findByEmail(email).isPresent();
    }

    /**
     * Validar que email tenga formato correcto
     */
    private boolean esEmailValido(String email) {
        String regex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return email != null && email.matches(regex);
    }

    /**
     * Validar rol
     */
    private boolean esRolValido(String rol) {
        return "CLIENTE".equals(rol) || "ENTRENADOR".equals(rol) || "ADMIN".equals(rol);
    }

    /**
     * Construir respuesta de login
     */
    private LoginResponseDTO buildLoginResponse(String token, String rol, String nombre, Integer idUsuario) {
        LoginResponseDTO res = new LoginResponseDTO();
        res.setToken(token);
        res.setRol(rol);
        res.setNombre(nombre);
        res.setIdUsuario(idUsuario);

        // Agregar IDs adicionales según el rol
        if ("CLIENTE".equals(rol)) {
            res.setIdCliente(idUsuario);
        } else if ("ENTRENADOR".equals(rol)) {
            res.setIdEntrenador(idUsuario);
        }

        return res;
    }
}