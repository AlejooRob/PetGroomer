package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Mascota;
import com.petgroomer.petgroomer.repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> buscarPorId(Long idMascota) {
        return mascotaRepository.findById(idMascota);
    }

    @Override
    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota actualizarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public void eliminarMascota(Long idMascota) {
        mascotaRepository.deleteById(idMascota);
    }
}
