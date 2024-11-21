package com.petgroomer.petgroomer.controllers;

import com.petgroomer.petgroomer.models.AppUser;
import com.petgroomer.petgroomer.models.Cliente;
import com.petgroomer.petgroomer.services.AppUserService;
import com.petgroomer.petgroomer.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.listarClientes();
        model.addAttribute("clientes", clientes);
        return "clientes/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistroCliente(Model model) {
        Cliente cliente = new Cliente();
        AppUser usuario = new AppUser();
        cliente.setUsuario(usuario); // Se crea un objeto AppUser y se asocia al Cliente
        model.addAttribute("cliente", cliente);
        return "clientes/registrar";
    }

    @PostMapping
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
        AppUser usuario = cliente.getUsuario();

        if (appUserService.existeEmail(usuario.getEmail())) {
            model.addAttribute("error", "El email ya está registrado. Intente con otro.");
            model.addAttribute("cliente", cliente); // Mantener los datos del cliente
            return "clientes/registrar";
        }

        try {

            usuario = appUserService.registrarUsuario(usuario);
            cliente.setUsuario(usuario);
            usuario.setCliente(cliente);
            clienteService.guardarCliente(cliente);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Hubo un problema al guardar los datos del cliente. Intente nuevamente.");
            model.addAttribute("cliente", cliente); // Mantener los datos del cliente
            return "clientes/registrar";
        }
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> clienteOpt = clienteService.buscarPorId(id);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
            return "clientes/editar";
        } else {
            return "redirect:/clientes";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute("cliente") Cliente cliente) {

        Optional<Cliente> clienteOpt = clienteService.buscarPorId(id);
        if (clienteOpt.isPresent()) {
            Cliente clienteExistente = clienteOpt.get();
            AppUser usuarioExistente = clienteExistente.getUsuario();
            usuarioExistente.setNombre(cliente.getUsuario().getNombre());
            usuarioExistente.setApellido(cliente.getUsuario().getApellido());
            usuarioExistente.setEmail(cliente.getUsuario().getEmail());
            usuarioExistente.setPassword(cliente.getUsuario().getPassword()); // Este debería manejarse con seguridad

            appUserService.registrarUsuario(usuarioExistente);

            clienteExistente.setTelefono(cliente.getTelefono());
            clienteExistente.setDireccion(cliente.getDireccion());
            clienteService.actualizarCliente(clienteExistente);
        }

        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = clienteService.buscarPorId(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            clienteService.eliminarCliente(id);
            appUserService.eliminarUsuario(cliente.getUsuario().getId());
        }
        return "redirect:/clientes";
    }
}