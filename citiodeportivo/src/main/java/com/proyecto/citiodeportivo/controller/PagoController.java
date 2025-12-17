package com.proyecto.citiodeportivo.controller;

import com.proyecto.citiodeportivo.dto.PagoRequestDTO;
import com.proyecto.citiodeportivo.dto.PagoResponseDTO;
import com.proyecto.citiodeportivo.entities.*;
import com.proyecto.citiodeportivo.dto.mapper.PagoMapper;
import com.proyecto.citiodeportivo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;
    private final ClienteService clienteService;
    private final ReservaService reservaService;
    private final AdministradorService adminService;
    private final PagoMapper mapper;

    @GetMapping
    public List<PagoResponseDTO> listar() {
        return pagoService.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toDTO(pagoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PagoResponseDTO> crear(@RequestBody PagoRequestDTO dto) {

        ReservaEntity reserva = reservaService.findById(dto.getIdReserva());
        ClienteEntity cliente = clienteService.findById(dto.getIdCliente());
        AdministradorEntity admin = null; // opcional

        PagoEntity pago = mapper.toEntity(dto, reserva, cliente, admin);

        return ResponseEntity.ok(mapper.toDTO(pagoService.save(pago)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody PagoRequestDTO dto) {

        ReservaEntity reserva = reservaService.findById(dto.getIdReserva());
        ClienteEntity cliente = clienteService.findById(dto.getIdCliente());
        AdministradorEntity admin = null;

        PagoEntity datos = mapper.toEntity(dto, reserva, cliente, admin);
        PagoEntity actualizado = pagoService.update(id, datos);

        return ResponseEntity.ok(mapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        pagoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
