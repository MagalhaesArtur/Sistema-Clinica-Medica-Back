package com.example.demo.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Attendant;
import com.example.demo.entities.User;

// Serviço de token
@Service
public class TokenService {
    private Integer expireToken = 1200000;
    // 1200000

    // Função que valida um token
    public boolean validateToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC512("secret")).build().verify(token);
        if (jwt instanceof DecodedJWT) {
            return true;
        } else {
            return false;
        }
    }

    // Gera um novo token para os usuários
    public String generateTokenUser(User user) {
        return JWT.create().withSubject(user.getEmail()).withClaim("isADM", false).withClaim("isATT", false)
                .withClaim("id", user.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireToken))
                .sign(Algorithm.HMAC512("secret"));
    }

    // Gera um token para o ADM
    public String generateTokenAdm(Admin adm) {
        return JWT.create().withSubject(adm.getEmail()).withClaim("isADM", true)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireToken))
                .sign(Algorithm.HMAC512("secret"));
    }

    // Gera um token para o atendente
    public String generateTokenAtt(Attendant att) {

        return JWT.create().withSubject(att.getEmail()).withClaim("isADM", false).withClaim("isATT", true)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireToken))
                .sign(Algorithm.HMAC512("secret"));
    }

    // Decodifica um token
    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC512("secret")).build().verify(token).getSubject();
    }
}
