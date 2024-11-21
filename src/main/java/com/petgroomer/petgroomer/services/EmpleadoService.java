package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> listarEmpleados();
    Optional<Empleado> buscarPorId(Long idEmpleado);
    Empleado guardarEmpleado(Empleado empleado);
    Empleado actualizarEmpleado(Empleado empleado);
    void eliminarEmpleado(Long idEmpleado);
}
