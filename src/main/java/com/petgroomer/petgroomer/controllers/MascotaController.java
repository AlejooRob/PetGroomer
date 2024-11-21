package com.petgroomer.petgroomer.controllers;

import com.petgroomer.petgroomer.models.Cliente;
import com.petgroomer.petgroomer.models.Mascota;
import com.petgroomer.petgroomer.services.ClienteService;
import com.petgroomer.petgroomer.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarMascotas(Model model) {
        List<Mascota> mascotas = mascotaService.listarMascotas();
        model.addAttribute("mascotas", mascotas);
        return "mascotas/listar"; // Nombre de la plantilla para listar mascotas
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistroMascota(Model model) {
        List<Cliente> clientes = clienteService.listarClientes();
        Mascota mascota = new Mascota();
        model.addAttribute("mascota", mascota);
        model.addAttribute("clientes", clientes);
        return "mascotas/registrar"; // Nombre de la plantilla para registrar mascota
    }

    @PostMapping
    public String guardarMascota(@ModelAttribute("mascota") Mascota mascota) {
        mascotaService.guardarMascota(mascota);
        return "redirect:/mascotas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarMascota(@PathVariable Long id, Model model) {
        Optional<Mascota> mascotaOpt = mascotaService.buscarPorId(id);
        if (mascotaOpt.isPresent()) {
            model.addAttribute("mascota", mascotaOpt.get());
            return "mascotas/editar"; // Nombre de la plantilla para editar mascota
        } else {
            return "redirect:/mascotas";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMascota(@PathVariable Long id, @ModelAttribute("mascota") Mascota mascota) {
        mascota.setIdMascota(id);
        mascotaService.actualizarMascota(mascota);
        return "redirect:/mascotas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return "redirect:/mascotas";
    }
}
