package com.example.demo.controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ConsultaMod;
import com.example.demo.dto.PersonalDate;
import com.example.demo.entities.Consulta;
import com.example.demo.services.ConsultaService;

@RestController
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @GetMapping("/consultas")
    public List<Consulta> findAll() {
        return service.findAll();
    }

    @GetMapping("/consultas/doc/{id}")
    public List<Consulta> findByDocId(@PathVariable Long id) {
        return service.findByDocId(id);
    }

    @GetMapping("/consultas/day")
    public List<Consulta> findByDay(@RequestBody PersonalDate date) throws ParseException {
        return service.findByDay(date);
    }

    @PostMapping("/createConsulta")
    public Consulta create(@RequestBody ConsultaMod consulta) throws ParseException {
        return service.createConsulta(consulta);
    }
}
