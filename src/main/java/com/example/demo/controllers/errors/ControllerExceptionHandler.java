package com.example.demo.controllers.errors;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.services.errors.EmailAlreadyExists;
import com.example.demo.services.errors.EntityNotFoundException;
import com.example.demo.services.errors.UnregistredEmail;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice

// Classe para criar erros personalizados
public class ControllerExceptionHandler {

    // Erro de Entidade não encontrada (repositórtio vazio)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err);
    }

    // Erro de email já existente
    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<StandardError> emailAlreadyExists(EmailAlreadyExists e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setError("Resource already in use");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(err);
    }

    // Erro de email não cadastrado
    @ExceptionHandler(UnregistredEmail.class)
    public ResponseEntity<StandardError> unregistredEmail(UnregistredEmail e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Email não cadastrado!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err);
    }

}
