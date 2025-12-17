package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_entrenamiento")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SolicitudEntrenamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private EntrenadorEntity entrenador;

    private LocalDateTime inicio;
    private LocalDateTime fin;

    private String estado;

    private LocalDateTime creadoEn;

    @PrePersist
    public void onCreate() {
        creadoEn = LocalDateTime.now();
    }

}
