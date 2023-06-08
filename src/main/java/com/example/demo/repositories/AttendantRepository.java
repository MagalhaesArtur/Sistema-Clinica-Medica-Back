package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Attendant;

public interface AttendantRepository extends JpaRepository<Attendant, Long> {
    Attendant findByEmail(String email);

}
