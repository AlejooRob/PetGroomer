package com.petgroomer.petgroomer.controllers;

import com.petgroomer.petgroomer.models.AppUser;
import com.petgroomer.petgroomer.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String mostrarFormularioRegistro(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("usuario", appUser);
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute("usuario") AppUser usuario) {
        appUserService.registrarUsuario(usuario);
        return "redirect:/login";
    }
}
