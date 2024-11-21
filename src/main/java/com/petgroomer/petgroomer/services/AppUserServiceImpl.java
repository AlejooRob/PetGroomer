package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.AppUser;
import com.petgroomer.petgroomer.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public AppUser registrarUsuario(AppUser usuario) {
        // Encriptar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar un rol por defecto (puede ser "USER" o "CLIENTE")
        usuario.setRol("USER");

        // Guardar el usuario en la base de datos
        return appUserRepository.save(usuario);
    }

    @Override
    public AppUser buscarUsuarioPorEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(Long idCita) {
        appUserRepository.deleteById(idCita);
    }

    @Override
    public boolean existeEmail(String email) {
        return appUserRepository.findByEmail(email) != null;
    }
}
