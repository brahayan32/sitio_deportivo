package com.proyecto.citiodeportivo.service.impl;

import com.proyecto.citiodeportivo.entities.ReservaEntity;
import com.proyecto.citiodeportivo.repository.ReservaRepository;
import com.proyecto.citiodeportivo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    @Override
    public List<ReservaEntity> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public ReservaEntity findById(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Override
    public ReservaEntity save(ReservaEntity reserva) {

        if (existeSolapamiento(
                reserva.getCancha().getIdCancha(),
                reserva.getInicio(),
                reserva.getFin()
        )) {
            throw new RuntimeException("La cancha está ocupada en ese horario.");
        }

        return reservaRepository.save(reserva);
    }

    @Override
    public ReservaEntity update(Integer id, ReservaEntity r) {
        ReservaEntity actual = findById(id);

        // si cambió de cancha, validar solapamiento
        if (
                !actual.getCancha().getIdCancha().equals(r.getCancha().getIdCancha()) ||
                        !actual.getInicio().equals(r.getInicio()) ||
                        !actual.getFin().equals(r.getFin())
        ) {
            if (existeSolapamiento(r.getCancha().getIdCancha(), r.getInicio(), r.getFin())) {
                throw new RuntimeException("La cancha está ocupada en ese horario.");
            }
        }


        actual.setCliente(r.getCliente());
        actual.setCancha(r.getCancha());
        actual.setTarifa(r.getTarifa());
        actual.setInicio(r.getInicio());
        actual.setFin(r.getFin());
        actual.setEstado(r.getEstado());
        actual.setTotalPagar(r.getTotalPagar());
        actual.setIncluirEntrenador(r.getIncluirEntrenador());

        return reservaRepository.save(actual);
    }

    @Override
    public void delete(Integer id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public boolean existeSolapamiento(Integer idCancha, LocalDateTime inicio, LocalDateTime fin) {
        return reservaRepository.existeSolapamiento(idCancha, inicio, fin) > 0;
    }
}
