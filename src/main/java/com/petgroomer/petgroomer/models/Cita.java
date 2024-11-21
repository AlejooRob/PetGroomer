package com.petgroomer.petgroomer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha;

    @ManyToMany
    @JoinTable(
            name = "cita_servicio",
            joinColumns = @JoinColumn(name = "cita_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private List<Servicio> servicios;

    private String descripcion;
}
