package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.SolicitudEntrenamientoRequestDTO;
import com.proyecto.citiodeportivo.dto.SolicitudEntrenamientoResponseDTO;
import com.proyecto.citiodeportivo.dto.mapper.SolicitudEntrenamientoMapper;
import com.proyecto.citiodeportivo.entities.SolicitudEntrenamientoEntity;
import com.proyecto.citiodeportivo.service.SolicitudEntrenamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudEntrenamientoController {

    private final SolicitudEntrenamientoService service;
    private final SolicitudEntrenamientoMapper mapper;

    @GetMapping("/entrenador/{idEntrenador}")
    public List<SolicitudEntrenamientoResponseDTO> listarPorEntrenador(@PathVariable Integer idEntrenador) {
        return service.findByEntrenador(idEntrenador)  // ← Llama a service
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

// Si tu controller no tiene inyectado el repository, agrégalo:
// @RequiredArgsConstructor
// private final SolicitudEntrenamientoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudEntrenamientoResponseDTO> obtener(@PathVariable Integer id) {
        SolicitudEntrenamientoEntity sol = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(sol));
    }

    @PostMapping
    public SolicitudEntrenamientoResponseDTO crear(@RequestBody SolicitudEntrenamientoRequestDTO dto) {
        SolicitudEntrenamientoEntity entity = mapper.toEntity(dto);
        return mapper.toResponse(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudEntrenamientoResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody SolicitudEntrenamientoRequestDTO dto
    ) {
        SolicitudEntrenamientoEntity actualizada =
                service.update(id, mapper.toEntity(dto));

        return ResponseEntity.ok(mapper.toResponse(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
