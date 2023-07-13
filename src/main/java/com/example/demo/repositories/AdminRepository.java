package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Admin;

// Repositório de ADMIN
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);

}
