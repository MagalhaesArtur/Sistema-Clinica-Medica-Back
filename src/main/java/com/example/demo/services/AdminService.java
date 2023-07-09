package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.Admin;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.services.errors.EmailAlreadyExists;
import com.example.demo.services.errors.UnregistredEmail;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Admin createAdmin(@RequestBody Admin admin) {
        Admin adminAux = new Admin();

        if (repository.findByEmail(admin.getEmail()) != null) {
            throw new EmailAlreadyExists("Email já em uso!");
        } else {
            adminAux.setEmail(admin.getEmail());
            adminAux.setId(admin.getId());
            adminAux.setPassword(encoder.encode(admin.getPassword()));
            adminAux.setUsername(admin.getUsername());

            return repository.save(adminAux);
        }

    }

    public List<Admin> findAll() {
        return repository.findAll();
    }

    public Admin login(@RequestBody Admin admin) {
        Admin userAux = repository.findByEmail(admin.getEmail());

        if (userAux == null) {
            throw new UnregistredEmail("Email não cadastrado!");
        } else {
            admin.getClass();
        }
        return userAux;

    }
}
