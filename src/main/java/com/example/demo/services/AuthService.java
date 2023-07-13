package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Admin;
import com.example.demo.entities.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.AttendantRepository;
import com.example.demo.repositories.UserRepository;

// Serviço de autenticação
@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AdminRepository admRepo;

    @Autowired
    private AttendantRepository attRepo;

    // Verificando de quem quer fazer login é usuário/atendente ou admin
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepo.findByEmail(username) instanceof User) {
            return userRepo.findByEmail(username);
        } else if (admRepo.findByEmail(username) instanceof Admin) {
            return admRepo.findByEmail(username);
        } else {
            return attRepo.findByEmail(username);
        }

    }

}
