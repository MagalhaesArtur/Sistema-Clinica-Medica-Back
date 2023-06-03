package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.errors.EmailAlreadyExists;
import com.example.demo.services.errors.EntityNotFoundException;
import com.example.demo.services.errors.UnregistredEmail;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public User findUserById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id not find " + id));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User createUser(@RequestBody User user) {
        User userAux = new User();

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExists("Email já em uso!");
        } else {
            userAux.setEmail(user.getEmail());
            userAux.setId(user.getId());
            userAux.setPassword(encoder.encode(user.getPassword()));
            userAux.setUsername(user.getUsername());

            return repository.save(userAux);
        }

    }

    public User login(@RequestBody User user) {
        User userAux = repository.findByEmail(user.getEmail());

        if (userAux == null) {
            throw new UnregistredEmail("Email não cadastrado!");
        } else {
            user.getClass();
        }
        return userAux;

    }
}
