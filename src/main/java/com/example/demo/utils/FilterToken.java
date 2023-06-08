package com.example.demo.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.AttendantRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AttendantRepository attRepo;

    @Autowired
    private AdminRepository admRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token;
        var auth = request.getHeader("Authorization");

        if (auth != null) {
            token = auth.replace("Bearer ", "");
            var subject = this.tokenService.getSubject(token);

            if (userRepo.findByEmail(subject) != null) {
                var user = this.userRepo.findByEmail(subject);

                var authorizarion = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authorizarion);
            } else if (admRepo.findByEmail(subject) != null) {
                var user = this.admRepo.findByEmail(subject);

                var authorizarion = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authorizarion);
            } else {
                var user = this.attRepo.findByEmail(subject);

                var authorizarion = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authorizarion);
            }

        }

        filterChain.doFilter(request, response);
    }

}
