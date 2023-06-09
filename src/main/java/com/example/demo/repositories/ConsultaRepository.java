package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Consulta;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.User;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByDoctor(Doctor doc);

    List<Consulta> findByDate(Date date);

    @Query("select a from Consulta a where a.date <= :date")
    List<Consulta> findAllWithCreationDateTimeBefore(
            @Param("date") Date date);

    List<Consulta> findByPatient(User user);

}
