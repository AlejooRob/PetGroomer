package com.petgroomer.petgroomer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String rol;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Empleado empleado;
}