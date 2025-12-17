package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @OneToOne
    @JoinColumn(name = "id_reserva", unique = true)
    private ReservaEntity reserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    private Double monto;
    private String metodo;
    private String estadoPago;

    private LocalDateTime fechaPago;

    @ManyToOne
    @JoinColumn(name = "procesado_por_admin")
    private AdministradorEntity procesadoPorAdmin;
}
