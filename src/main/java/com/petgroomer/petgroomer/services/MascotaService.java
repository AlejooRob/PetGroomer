package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaService {
    List<Mascota> listarMascotas();
    Optional<Mascota> buscarPorId(Long idMascota);
    Mascota guardarMascota(Mascota mascota);
    Mascota actualizarMascota(Mascota mascota);
    void eliminarMascota(Long idMascota);
}
