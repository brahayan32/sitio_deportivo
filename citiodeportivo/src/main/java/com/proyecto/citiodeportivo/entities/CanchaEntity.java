package com.proyecto.citiodeportivo.entities;

import com.proyecto.citiodeportivo.entities.enums.EstadoCancha;
import com.proyecto.citiodeportivo.entities.enums.TipoCancha;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cancha")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CanchaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCancha;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCancha tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCancha estado;

    private String descripcion;

    private LocalDateTime ultimaModificacion;

    @ManyToOne
    @JoinColumn(name = "modificada_por_admin")
    private AdministradorEntity modificadaPorAdmin;
}
