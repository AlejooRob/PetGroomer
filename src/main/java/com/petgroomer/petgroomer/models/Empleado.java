package com.petgroomer.petgroomer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private AppUser usuario;

    private String cargo;
    private String telefono;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Cita> citas;
}