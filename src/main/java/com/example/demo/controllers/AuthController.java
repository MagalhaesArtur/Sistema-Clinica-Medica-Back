package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.dto.JsonToken;
import com.example.demo.dto.Login;
import com.example.demo.dto.UserAndToken;
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

    // Retorna True se o token fornecido estiver válido
    @PostMapping("/auth")
    public boolean authToken(@RequestBody JsonToken token) {
        try {
            return tokenService.validateToken(token.token);
        } catch (JWTVerificationException e) {
            if (e.getMessage().contains("The Token has expired")) {
                return false;
            } else {
                return true;
            }
        }
    }

    // Faz a operação de login e trata alguns casos de erro.
    @PostMapping("/login")
    public UserAndToken login(@RequestBody Login login) {
        User userAux = userRepo.findByEmail(login.email());
        Admin admAux = admRepo.findByEmail(login.email());
        Attendant attAux = attRepo.findByEmail(login.email());

        if (admAux == null && userAux == null && attAux == null) {
            throw new UnregistredEmail("Email não !");
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.email(),
                login.password());
        try {

            Authentication authenticate = this.authManager.authenticate(authToken);

            if (authenticate.getPrincipal() instanceof User) {
                userAux.setPassword(null);

                return new UserAndToken<User>(userAux,
                        tokenService.generateTokenUser((User) authenticate.getPrincipal()));
            } else if (authenticate.getPrincipal() instanceof Admin) {
                admAux.setPassword(null);

                return new UserAndToken<Admin>(admAux,
                        tokenService.generateTokenAdm((Admin) authenticate.getPrincipal()));

            } else {
                attAux.setPassword(null);

                return new UserAndToken<Attendant>(attAux,
                        tokenService.generateTokenAtt((Attendant) authenticate.getPrincipal()));
            }
        } catch (BadCredentialsException e) {
            throw e;
        }

    }
}
