package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.entities.ClienteEntity;
import com.proyecto.citiodeportivo.repository.ClienteRepository;
import com.proyecto.citiodeportivo.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder encoder;

    @Override
    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteEntity findById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public ClienteEntity save(ClienteEntity cliente) {

        // Si trae contraseña nueva → encriptarla
        if (cliente.getPasswordHash() != null && !cliente.getPasswordHash().isBlank()) {
            cliente.setPasswordHash(encoder.encode(cliente.getPasswordHash()));
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteEntity update(Integer id, ClienteEntity datos) {
        ClienteEntity c = findById(id);

        c.setNombre(datos.getNombre());
        c.setApellido(datos.getApellido());
        c.setEmail(datos.getEmail());
        c.setTelefono(datos.getTelefono());

        // Si viene contraseña nueva → cambiarla
        if (datos.getPasswordHash() != null && !datos.getPasswordHash().isBlank()) {
            c.setPasswordHash(encoder.encode(datos.getPasswordHash()));
        }

        return clienteRepository.save(c);
    }

    @Override
    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteEntity findByEmail(String email) {
        return clienteRepository.findByEmail(email).orElse(null);
    }
}
