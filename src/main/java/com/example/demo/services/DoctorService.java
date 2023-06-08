package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Doctor;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.services.errors.EntityNotFoundException;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public List<Doctor> findAll() {
        return repository.findAll();
    }

    public Doctor findDocById(Long id) {
        Optional<Doctor> docAux = repository.findById(id);
        if (docAux.isPresent()) {
            return docAux.get();
        } else {
            throw new EntityNotFoundException("Médico não encontrado com ID " + id);
        }

    }

}
