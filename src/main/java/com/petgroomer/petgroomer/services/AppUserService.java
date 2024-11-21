package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.AppUser;

public interface AppUserService {
    AppUser registrarUsuario(AppUser usuario);
    AppUser buscarUsuarioPorEmail(String email);
    void eliminarUsuario(Long idCita);

    boolean existeEmail(String email);
}
