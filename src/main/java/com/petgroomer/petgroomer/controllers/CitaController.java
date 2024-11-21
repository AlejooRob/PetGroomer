package com.petgroomer.petgroomer.controllers;

import com.petgroomer.petgroomer.models.*;
import com.petgroomer.petgroomer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String listarCitas(Model model) {
        List<Cita> citas = citaService.listarCitas();
        model.addAttribute("citas", citas);
        return "citas/listar";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioRegistroCita(Model model) {

        List<Servicio> servicios = servicioService.listarServicios();
        List<Cliente> clientes = clienteService.listarClientes();
        List<Empleado> empleados = empleadoService.listarEmpleados();
        List<Mascota> mascotas = mascotaService.listarMascotas();
        Cita cita = new Cita();
        model.addAttribute("cita", cita);
        model.addAttribute("clientes", clientes);
        model.addAttribute("empleados", empleados);
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("servicios", servicios);
        return "citas/registrar";
    }

    @PostMapping
    public String guardarCita(@ModelAttribute("cita") Cita cita, Model model) {
        List<Servicio> serviciosSelects = cita.getServicios().stream()
                        .map(servicio -> servicioService.buscarPorId(servicio.getIdServicio()).orElse(null))
                                .filter(servicio -> servicio != null)
                                .collect(Collectors.toList());
        cita.setServicios(serviciosSelects);
        citaService.guardarCita(cita);

        Optional<Cliente> clienteOptional = clienteService.buscarPorId(cita.getCliente().getIdCliente());

        Cliente cliente = clienteOptional.get();

        // Depuración para verificar el cliente y su usuario
        if (cliente == null) {

            System.out.println("Cliente es null");
        } else {
            System.out.println("Cita Id: "+cita.getIdCita());
            System.out.println("Cliente ID: " + cliente.getIdCliente());
            if (cliente.getUsuario() == null) {
                System.out.println("Usuario es null");
            } else {
                System.out.println("Usuario Email: " + cliente.getUsuario().getEmail());
            }
        }

        if(cliente != null && cliente.getUsuario() != null) {
            AppUser appUser = cliente.getUsuario();
            String  emailCliente = appUser.getEmail();
            //creo el objeto email para enviar
            Email email = new Email();

            email.setAddress(emailCliente);
            email.setSubject("Confirmacion de cita - PetGroomer");

            String emailBody = String.format(
                    "Hola %s,\n\nTu cita ha sido registrada exitosamente.\n\nDetalles de la cita:\n- Fecha: %s\n- Servicios: %s\n\nGracias por confiar en PetGroomer.",
                    cliente.getUsuario().getNombre(),
                    cita.getFecha().toString(),
                    serviciosSelects.stream().map(Servicio::getNombre).collect(Collectors.joining(", "))
            );
            email.setBody(emailBody);

            try {
                emailService.sendMail(email, cita);
            } catch (Exception e) {
                model.addAttribute("error", "Hubo un problema al enviar el correo electrónico.");
            }
        } else {
            System.out.println("El cilente asociado al usuario no existe, no se encviara al correo");
        }
        return "redirect:/citas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCita(@PathVariable Long id, Model model) {
        Optional<Cita> citaOpt = citaService.buscarPorId(id);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            model.addAttribute("cita", cita);
            model.addAttribute("clientes", clienteService.listarClientes());
            model.addAttribute("empleados", empleadoService.listarEmpleados());
            model.addAttribute("mascotas", mascotaService.listarMascotas());
            model.addAttribute("servicios", servicioService.listarServicios());
            return "citas/editar";
        } else {
            return "redirect:/citas";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCita(@PathVariable Long id, @ModelAttribute("cita") Cita cita) {
        cita.setIdCita(id);
        // Actualizar la cita con los nuevos servicios
        citaService.actualizarCita(cita);
        return "redirect:/citas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return "redirect:/citas";
    }
}
