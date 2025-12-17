package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.ReporteRequestDTO;
import com.proyecto.citiodeportivo.dto.ReporteResponseDTO;
import com.proyecto.citiodeportivo.entities.ReporteEntity;
import com.proyecto.citiodeportivo.repository.ReporteRepository;
import com.proyecto.citiodeportivo.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping
    public List<ReporteResponseDTO> listar() {
        return reporteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteService.findById(id));
    }

    @PostMapping
    public ReporteResponseDTO crear(@RequestBody ReporteRequestDTO dto) {
        return reporteService.save(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        reporteService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/filtro/fechas")
    public List<ReporteResponseDTO> porFechas(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fin) {
        return reporteService.filtrarPorFechas(inicio, fin);
    }

    @GetMapping("/filtro/cancha/{idCancha}")
    public List<ReporteResponseDTO> porCancha(@PathVariable Integer idCancha) {
        return reporteService.filtrarPorCancha(idCancha);
    }

    @GetMapping("/filtro/cliente/{idCliente}")
    public List<ReporteResponseDTO> porCliente(@PathVariable Integer idCliente) {
        return reporteService.filtrarPorCliente(idCliente);
    }
}
