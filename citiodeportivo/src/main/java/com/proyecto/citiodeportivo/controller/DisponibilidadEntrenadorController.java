package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.DisponibilidadRequestDTO;
import com.proyecto.citiodeportivo.dto.DisponibilidadResponseDTO;
import com.proyecto.citiodeportivo.entities.DisponibilidadEntrenadorEntity;
import com.proyecto.citiodeportivo.repository.DisponibilidadEntrenadorRepository;
import com.proyecto.citiodeportivo.repository.EntrenadorRepository;
import com.proyecto.citiodeportivo.service.DisponibilidadEntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidad")
@RequiredArgsConstructor
public class DisponibilidadEntrenadorController {

    private final DisponibilidadEntrenadorService service;
    private final EntrenadorRepository entrenadorRepository;
    private final com.proyecto.citiodeportivo.dto.mapper.DisponibilidadMapper mapper;

    @GetMapping("/entrenador/{idEntrenador}")
    public List<DisponibilidadResponseDTO> listarPorEntrenador(
            @PathVariable Integer idEntrenador
    ) {
        return service.findByEntrenador(idEntrenador)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadResponseDTO> obtener(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(
                    mapper.toDTO(service.findById(id))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DisponibilidadResponseDTO> crear(@RequestBody DisponibilidadRequestDTO request) {

        var entrenador = entrenadorRepository.findById(request.getIdEntrenador())
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        var entity = mapper.toEntity(request, entrenador);

        return ResponseEntity.ok(
                mapper.toDTO(service.save(entity))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody DisponibilidadRequestDTO request
    ) {
        var entrenador = entrenadorRepository.findById(request.getIdEntrenador())
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        var updatedEntity = mapper.toEntity(request, entrenador);

        return ResponseEntity.ok(
                mapper.toDTO(service.update(id, updatedEntity))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

