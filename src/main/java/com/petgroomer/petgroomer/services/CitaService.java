package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<Cita> listarCitas();
    Optional<Cita> buscarPorId(Long idCita);
    Cita guardarCita(Cita cita);
    Cita actualizarCita(Cita cita);
    void eliminarCita(Long idCita);
}
