package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Servicio;
import com.petgroomer.petgroomer.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public Optional<Servicio> buscarPorId(Long idServicio) {
        return servicioRepository.findById(idServicio);
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio actualizarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminarServicio(Long idServicio) {
        servicioRepository.deleteById(idServicio);
    }
}
