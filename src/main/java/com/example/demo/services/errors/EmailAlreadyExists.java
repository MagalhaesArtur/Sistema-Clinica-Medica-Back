package com.example.demo.services.errors;

// Erro de email já existente 
public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String message) {
        super(message);
    }
}
