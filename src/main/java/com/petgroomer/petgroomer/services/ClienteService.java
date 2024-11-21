package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarClientes();
    Optional<Cliente> buscarPorId(Long idCliente);
    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    void eliminarCliente(Long idCliente);
}
