package com.example.demo.services.errors;

// Erro de entidade não encontrada
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
