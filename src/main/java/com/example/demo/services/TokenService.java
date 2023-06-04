package com.example.demo.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entities.Admin;
import com.example.demo.entities.User;

@Service
public class TokenService {
    public String generateTokenUser(User user) {
        return JWT.create().withSubject(user.getEmail()).withClaim("isADM", false)
                .withExpiresAt(new Date(System.currentTimeMillis() + 600_000))
                .sign(Algorithm.HMAC512("secret"));
    }

    public String generateTokenAdm(Admin user) {
        return JWT.create().withSubject(user.getEmail()).withClaim("isADM", true)
                .withExpiresAt(new Date(System.currentTimeMillis() + 600_000))
                .sign(Algorithm.HMAC512("secret"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC512("secret")).build().verify(token).getSubject();
    }
}
