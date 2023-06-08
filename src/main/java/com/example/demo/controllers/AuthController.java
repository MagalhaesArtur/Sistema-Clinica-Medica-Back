package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Login;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Attendant;
import com.example.demo.entities.User;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.AttendantRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TokenService;
import com.example.demo.services.errors.UnregistredEmail;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AttendantRepository attRepo;

    @Autowired
    private AdminRepository admRepo;

    @PostMapping("/login")
    public String login(@RequestBody Login login) {
        User userAux = userRepo.findByEmail(login.email());
        Admin admAux = admRepo.findByEmail(login.email());
        Attendant attAux = attRepo.findByEmail(login.email());

        if (admAux == null && userAux == null && attAux == null) {
            throw new UnregistredEmail("Email não cadastrado!");
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.email(),
                login.password());

        Authentication authenticate = this.authManager.authenticate(authToken);

        if (authenticate.getPrincipal() instanceof User) {
            return tokenService.generateTokenUser((User) authenticate.getPrincipal());
        } else if (authenticate.getPrincipal() instanceof Admin) {
            return tokenService.generateTokenAdm((Admin) authenticate.getPrincipal());
        } else {
            return tokenService.generateTokenAtt((Attendant) authenticate.getPrincipal());
        }

    }
}
