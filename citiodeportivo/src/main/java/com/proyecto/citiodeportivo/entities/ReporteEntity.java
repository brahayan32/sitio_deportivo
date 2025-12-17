package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reporte")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReporteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReporte;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private AdministradorEntity administrador;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private ReservaEntity reserva;

    private LocalDateTime fechaGenerado;

    private String tipoReporte;

    private String descripcion;
}
