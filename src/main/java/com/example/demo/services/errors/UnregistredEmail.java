package com.example.demo.services.errors;

// Erro de email não cadastrado
public class UnregistredEmail extends RuntimeException {
    public UnregistredEmail(String message) {
        super(message);
    }
}
