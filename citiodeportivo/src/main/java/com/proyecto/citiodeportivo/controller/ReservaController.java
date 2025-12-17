package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.ReservaRequestDTO;
import com.proyecto.citiodeportivo.dto.ReservaResponseDTO;
import com.proyecto.citiodeportivo.dto.mapper.ReservaMapper;
import com.proyecto.citiodeportivo.entities.ReservaEntity;
import com.proyecto.citiodeportivo.repository.ReservaRepository;
import com.proyecto.citiodeportivo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final ReservaMapper reservaMapper;

    @GetMapping
    public List<ReservaResponseDTO> listar() {
        return reservaService.findAll()
                .stream()
                .map(reservaMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> obtener(@PathVariable Integer id) {
        ReservaEntity reserva = reservaService.findById(id);
        return ResponseEntity.ok(reservaMapper.toResponse(reserva));
    }

    @PostMapping
    public ReservaResponseDTO crear(@RequestBody ReservaRequestDTO dto) {
        ReservaEntity entity = reservaMapper.toEntity(dto);
        ReservaEntity guardada = reservaService.save(entity);
        return reservaMapper.toResponse(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody ReservaRequestDTO dto
    ) {
        ReservaEntity entity = reservaMapper.toEntity(dto);
        ReservaEntity actualizada = reservaService.update(id, entity);
        return ResponseEntity.ok(reservaMapper.toResponse(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        reservaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
