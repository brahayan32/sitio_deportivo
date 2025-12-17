package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarifa")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TarifaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarifa;

    @Column(name = "tipo_servicio")
    private String tipoServicio;

    @Column(name = "precio_hora")
    private Double precioHora;

    private Boolean vigente;

    private LocalDateTime creadoEn;

    @ManyToOne
    @JoinColumn(name = "creado_por_admin")
    private AdministradorEntity creadoPorAdmin;

    @PrePersist
    public void onCreate() {
        creadoEn = LocalDateTime.now();

    }

}
