package com.petgroomer.petgroomer.controllers;

import com.petgroomer.petgroomer.models.AppUser;
import com.petgroomer.petgroomer.models.Empleado;
import com.petgroomer.petgroomer.services.AppUserService;
import com.petgroomer.petgroomer.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        model.addAttribute("empleados", empleados);
        return "empleados/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistroEmpleado(Model model) {
        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        return "empleados/registrar";
    }

    @PostMapping
    public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado, Model model) {
        try {
            AppUser usuario = empleado.getUsuario();
            appUserService.registrarUsuario(usuario);
            empleado.setUsuario(usuario);
            usuario.setEmpleado(empleado);
            empleadoService.guardarEmpleado(empleado);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "El email ya está registrado. Intente con otro.");
            return "empleados/registrar";
        }
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEmpleado(@PathVariable Long id, Model model) {
        Optional<Empleado> empleadoOpt = empleadoService.buscarPorId(id);
        if (empleadoOpt.isPresent()) {
            model.addAttribute("empleado", empleadoOpt.get());
            return "empleados/editar";
        } else {
            return "redirect:/empleados";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute("empleado") Empleado empleado) {
        empleado.setIdEmpleado(id);
        empleadoService.actualizarEmpleado(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleados";
    }
}