package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "administrador")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AdministradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdmin;

    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)  // ✅ Agregar nullable = false
    private String usuario;

    @Column(nullable = false)  // ✅ Agregar nullable = false
    private String passwordHash;

    private String rol;  // "ADMIN"

    // ✅ AGREGAR ESTOS CAMPOS
    @Column(unique = true)
    private String email;

    private String telefono;

    private LocalDateTime creadoEn;

    // ✅ AGREGAR @PrePersist
    @PrePersist
    public void onCreate() {
        if (this.creadoEn == null) {
            this.creadoEn = LocalDateTime.now();
        }
    }
}
