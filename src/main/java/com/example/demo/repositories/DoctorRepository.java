package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Doctor;

// Repositório de Médicos
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Doctor findByEmail(String email);

}
