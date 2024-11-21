package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Cita;
import com.petgroomer.petgroomer.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Optional<Cita> buscarPorId(Long idCita) {
        return citaRepository.findById(idCita);
    }

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public Cita actualizarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public void eliminarCita(Long idCita) {
        citaRepository.deleteById(idCita);
    }
}