package com.example.demo.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.User;

// Repositório de Usuários
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
