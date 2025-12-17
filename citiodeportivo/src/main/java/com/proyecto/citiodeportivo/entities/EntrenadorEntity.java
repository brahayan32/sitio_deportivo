// 2. EntrenadorEntity
package com.proyecto.citiodeportivo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "entrenador")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EntrenadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrenador;

    private String nombre;
    private String apellido;
    private String especialidad;  // ✅ Cambiar a "especialidad" o agregar "Especialización"

    @Column(unique = true, nullable = false)  // ✅ Agregar nullable = false
    private String email;

    private String telefono;

    @Column(nullable = false)  // ✅ Agregar nullable = false
    private String passwordHash;

    // ✅ AGREGAR ESTOS CAMPOS
    @Column(unique = true)
    private String documento;

    private String rol;  // "ENTRENADOR"

    private LocalDateTime creadoEn;

    // ✅ AGREGAR @PrePersist
    @PrePersist
    public void onCreate() {
        if (this.creadoEn == null) {
            this.creadoEn = LocalDateTime.now();
        }
    }
}
