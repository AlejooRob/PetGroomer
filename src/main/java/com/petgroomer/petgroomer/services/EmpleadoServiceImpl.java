package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Empleado;
import com.petgroomer.petgroomer.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> buscarPorId(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado actualizarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Long idEmpleado) {
        empleadoRepository.deleteById(idEmpleado);
    }
}
