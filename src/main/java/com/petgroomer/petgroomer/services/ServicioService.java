package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> listarServicios();
    Optional<Servicio> buscarPorId(Long idServicio);
    Servicio guardarServicio(Servicio servicio);
    Servicio actualizarServicio(Servicio servicio);
    void eliminarServicio(Long idServicio);
}
