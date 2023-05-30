package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public User findUserById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws Exception {
        // System.out.println(user.getEmail());
        User test = repository.findByEmail(user.getEmail());
        if (test != null) {
            throw new Exception("Email already exists");
        } else {
            return repository.save(user);
        }
    }
}
