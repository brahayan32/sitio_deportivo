package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)  // ✅ Agregar nullable = false
    private String email;

    @Column(nullable = false)  // ✅ Agregar nullable = false
    private String passwordHash;

    private String telefono;

    // ✅ AGREGAR ESTOS CAMPOS
    @Column(unique = true)
    private String documento;

    private String rol;  // "CLIENTE"

    private LocalDateTime creadoEn;

    // ✅ AGREGAR @PrePersist
    @PrePersist
    public void onCreate() {
        if (this.creadoEn == null) {
            this.creadoEn = LocalDateTime.now();
        }
    }
}
