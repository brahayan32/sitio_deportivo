package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_cancha", nullable = false)
    private CanchaEntity cancha;

    @ManyToOne
    @JoinColumn(name = "id_tarifa")
    private TarifaEntity tarifa;

    private LocalDateTime inicio;
    private LocalDateTime fin;

    private Boolean incluirEntrenador;

    private String estado;

    private Double totalPagar;

    private LocalDateTime creadoEn;
    @PrePersist
    public void prePersist() {
        this.creadoEn = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "creado_por_admin")
    private AdministradorEntity creadoPorAdmin;
}
