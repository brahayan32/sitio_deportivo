package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "disponibilidad_entrenador")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DisponibilidadEntrenadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisponibilidad;

    @ManyToOne
    @JoinColumn(name = "id_entrenador", nullable = false)
    private EntrenadorEntity entrenador;

    private String diaSemana;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    private LocalDateTime creadoEn;
}
