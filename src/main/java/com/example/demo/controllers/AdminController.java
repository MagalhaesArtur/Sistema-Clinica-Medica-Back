package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Admin;
import com.example.demo.services.AdminService;

@RestController
public class AdminController {
    @Autowired
    private AdminService admService;

    @GetMapping("/usersADM")
    public List<Admin> findAll() {
        return admService.findAll();
    }

}
