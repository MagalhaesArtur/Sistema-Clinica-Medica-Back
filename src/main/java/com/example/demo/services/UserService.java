package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.errors.EmailAlreadyExists;
import com.example.demo.services.errors.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User findUserById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id not find " + id));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User createUser(@RequestBody User user) {

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExists("Email já em uso!");
        } else {
            return repository.save(user);
        }

    }
}
