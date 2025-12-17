package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.DisponibilidadResponseDTO;
import com.proyecto.citiodeportivo.dto.EntrenadorRequestDTO;
import com.proyecto.citiodeportivo.dto.EntrenadorResponseDTO;
import com.proyecto.citiodeportivo.entities.EntrenadorEntity;
import com.proyecto.citiodeportivo.repository.EntrenadorRepository;
import com.proyecto.citiodeportivo.service.DisponibilidadEntrenadorService;
import com.proyecto.citiodeportivo.service.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrenadores")
@RequiredArgsConstructor
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @GetMapping
    public List<EntrenadorResponseDTO> listar() {
        return entrenadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(entrenadorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EntrenadorResponseDTO> crear(@RequestBody EntrenadorRequestDTO dto) {
        return ResponseEntity.ok(entrenadorService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody EntrenadorRequestDTO dto) {

        return ResponseEntity.ok(entrenadorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        entrenadorService.delete(id);
        return ResponseEntity.ok().build();
    }

}
