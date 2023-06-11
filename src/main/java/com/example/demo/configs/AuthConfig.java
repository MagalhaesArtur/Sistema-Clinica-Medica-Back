package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.utils.FilterToken;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Autowired
    private FilterToken filter;

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity code) throws Exception {
        return code.headers().frameOptions().sameOrigin().and().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/users/**").access(AuthorizationManagers.allOf(
                        AuthorityAuthorizationManager.hasAnyAuthority("ROLE_ADMIN", "ROLE_ATTENDANT")))
                .requestMatchers(HttpMethod.DELETE,
                        "/users/delete/**")
                .access(AuthorizationManagers.allOf(
                        AuthorityAuthorizationManager.hasAnyAuthority("ROLE_ADMIN", "ROLE_ATTENDANT")))

                .requestMatchers(HttpMethod.GET, "/doctors/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/createConsulta").authenticated()
                .anyRequest().permitAll()
                .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
