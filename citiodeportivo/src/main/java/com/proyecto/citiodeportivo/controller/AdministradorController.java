package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.AdministradorRequestDTO;
import com.proyecto.citiodeportivo.dto.AdministradorResponseDTO;
import com.proyecto.citiodeportivo.entities.AdministradorEntity;
import com.proyecto.citiodeportivo.repository.AdministradorRepository;
import com.proyecto.citiodeportivo.service.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService adminService;

    @GetMapping
    public List<AdministradorResponseDTO> listar() {
        return adminService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(adminService.findById(id));
    }

    @PostMapping
    public AdministradorResponseDTO crear(@RequestBody AdministradorRequestDTO dto) {
        return adminService.save(dto);
    }

    @PutMapping("/{id}")
    public AdministradorResponseDTO actualizar(@PathVariable Integer id,
                                               @RequestBody AdministradorRequestDTO dto) {
        return adminService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        adminService.delete(id);
        return ResponseEntity.ok().build();
    }
}
