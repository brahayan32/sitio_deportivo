package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.entities.PagoEntity;
import com.proyecto.citiodeportivo.repository.PagoRepository;
import com.proyecto.citiodeportivo.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    @Override
    public List<PagoEntity> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public PagoEntity findById(Integer id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    @Override
    public PagoEntity save(PagoEntity pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public PagoEntity update(Integer id, PagoEntity datos) {
        PagoEntity actual = findById(id);

        actual.setMonto(datos.getMonto());
        actual.setMetodo(datos.getMetodo());
        actual.setEstadoPago(datos.getEstadoPago());
        actual.setCliente(datos.getCliente());
        actual.setReserva(datos.getReserva());
        actual.setProcesadoPorAdmin(datos.getProcesadoPorAdmin());

        return pagoRepository.save(actual);
    }

    @Override
    public void delete(Integer id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public List<PagoEntity> findByCliente(Integer idCliente) {
        return pagoRepository.findByCliente_IdCliente(idCliente);
    }
}
