package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.ClienteRequestDTO;
import com.proyecto.citiodeportivo.dto.ClienteResponseDTO;
import com.proyecto.citiodeportivo.dto.mapper.ClienteMapper;
import com.proyecto.citiodeportivo.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper mapper;


    @GetMapping
    public List<ClienteResponseDTO> listarClientes() {
        return clienteService.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerCliente(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toDTO(clienteService.findById(id)));
    }

    @PostMapping
    public ClienteResponseDTO crearCliente(@RequestBody ClienteRequestDTO dto) {
        return mapper.toDTO(clienteService.save(mapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(
            @PathVariable Integer id,
            @RequestBody ClienteRequestDTO dto
    ) {
        return ResponseEntity.ok(
                mapper.toDTO(clienteService.update(id, mapper.toEntity(dto)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

}
