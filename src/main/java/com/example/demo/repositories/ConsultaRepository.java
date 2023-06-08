package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Consulta;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.User;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByDoctor(Doctor doc);

    List<Consulta> findByDate(Date date);

    List<Consulta> findByPatient(User user);

}
