package com.example.demo.services.errors;

public class UnregistredEmail extends RuntimeException {
    public UnregistredEmail(String message) {
        super(message);
    }
}
