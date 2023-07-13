package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JsonUUID;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Retorna todos os usuários
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    // Retorna um usuário pelo ID
    @GetMapping(value = "users/{id}")
    public User findUserById(@PathVariable UUID id) {
        return userService.findUserById(id);
    }

    // Deleta um usuário
    @DeleteMapping(value = "/delete/user")
    public User deleteUserById(@RequestBody JsonUUID id) {
        UUID uuidd = UUID.fromString(id.id);
        return userService.deleteUserById(uuidd);
    }

    // Registra um usuário
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
