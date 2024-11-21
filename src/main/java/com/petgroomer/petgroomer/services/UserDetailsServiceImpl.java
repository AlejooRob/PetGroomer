package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.AppUser;
import com.petgroomer.petgroomer.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);

        if (appUser == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }

        return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(appUser.getRol()) // Esto considera que los roles están almacenados como strings
                .build();
    }
}
