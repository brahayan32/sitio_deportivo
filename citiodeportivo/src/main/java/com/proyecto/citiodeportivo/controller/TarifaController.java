package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.TarifaRequestDTO;
import com.proyecto.citiodeportivo.dto.TarifaResponseDTO;
import com.proyecto.citiodeportivo.dto.mapper.TarifaMapper;
import com.proyecto.citiodeportivo.entities.TarifaEntity;
import com.proyecto.citiodeportivo.service.TarifaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifas")
@RequiredArgsConstructor
public class TarifaController {

    private final TarifaService tarifaService;
    private final TarifaMapper mapper;

    @GetMapping
    public List<TarifaResponseDTO> listar() {
        return tarifaService.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarifaResponseDTO> obtener(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(
                    mapper.toResponse(tarifaService.findById(id))
            );
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public TarifaResponseDTO crear(@RequestBody TarifaRequestDTO dto) {
        return mapper.toResponse(
                tarifaService.save(mapper.toEntity(dto))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaResponseDTO> actualizar(@PathVariable Integer id, @RequestBody TarifaRequestDTO dto) {
        try {
            TarifaEntity actualizada = tarifaService.update(id, dto);
            return ResponseEntity.ok(mapper.toResponse(actualizada));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        tarifaService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Filtros
    @GetMapping("/tipo/{tipo}")
    public List<TarifaResponseDTO> buscarPorTipo(@PathVariable String tipo) {
        return tarifaService.findByTipoServicio(tipo)
                .stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/vigentes")
    public List<TarifaResponseDTO> buscarVigentes() {
        return tarifaService.findVigentes()
                .stream().map(mapper::toResponse).toList();
    }
    @GetMapping("/por-cancha/{idCancha}")
    public ResponseEntity<TarifaResponseDTO> obtenerPorCancha(
            @PathVariable Integer idCancha
    ) {
        try {
            TarifaEntity tarifa = tarifaService.findTarifaVigentePorCancha(idCancha);
            return ResponseEntity.ok(mapper.toResponse(tarifa));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

