package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Doctor;
import com.example.demo.services.DoctorService;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService docService;

    // Retorna uma lista com todos os médicos
    @GetMapping("/doctors")
    public List<Doctor> findAll() {
        return docService.findAll();
    }

    // Retorna um médico com um certo ID
    @GetMapping("/doctors/{id}")
    public List<Doctor> findDocById(@PathVariable Long id) {
        return docService.findAll();
    }
}
