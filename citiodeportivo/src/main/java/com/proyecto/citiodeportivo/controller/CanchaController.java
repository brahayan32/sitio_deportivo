package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.CanchaRequestDTO;
import com.proyecto.citiodeportivo.dto.CanchaResponseDTO;
import com.proyecto.citiodeportivo.service.CanchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canchas")
@RequiredArgsConstructor
public class CanchaController {

    private final CanchaService canchaService;

    @GetMapping
    public List<CanchaResponseDTO> listar() {
        return canchaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanchaResponseDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(canchaService.findById(id));
    }

    @PostMapping
    public CanchaResponseDTO crear(@RequestBody CanchaRequestDTO dto) {
        return canchaService.save(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CanchaResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody CanchaRequestDTO dto) {

        return ResponseEntity.ok(canchaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        canchaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
