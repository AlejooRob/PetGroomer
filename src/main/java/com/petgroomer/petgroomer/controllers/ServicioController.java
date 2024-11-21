package com.petgroomer.petgroomer.controllers;

import com.petgroomer.petgroomer.models.Servicio;
import com.petgroomer.petgroomer.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public String listarServicios(Model model) {
        List<Servicio> servicios = servicioService.listarServicios();
        model.addAttribute("servicios", servicios);
        return "servicios/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistroServicio(Model model) {
        Servicio servicio = new Servicio();
        model.addAttribute("servicio", servicio);
        return "servicios/registrar";
    }

    @PostMapping
    public String guardarServicio(@ModelAttribute("servicio") Servicio servicio) {
        servicioService.guardarServicio(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarServicio(@PathVariable Long id, Model model) {
        Optional<Servicio> servicioOpt = servicioService.buscarPorId(id);
        if (servicioOpt.isPresent()) {
            model.addAttribute("servicio", servicioOpt.get());
            return "servicios/editar";
        } else {
            return "redirect:/servicios";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarServicio(@PathVariable Long id, @ModelAttribute("servicio") Servicio servicio) {
        servicio.setIdServicio(id);
        servicioService.actualizarServicio(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return "redirect:/servicios";
    }
}
