package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ConsultaMod;
import com.example.demo.dto.PersonalDate;
import com.example.demo.entities.Consulta;
import com.example.demo.entities.Doctor;
import com.example.demo.repositories.ConsultaRepository;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.services.errors.EntityNotFoundException;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private DoctorRepository docRepo;

    @Autowired
    private DoctorService docService;

    @Autowired
    private UserService userService;

    public List<Consulta> findAll() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    public List<Consulta> findByDay(PersonalDate date) throws ParseException {
        String ano = date.ano;
        String mes = date.mes;
        String dia = date.dia;
        List<Consulta> result = repository.findByDate(
                new SimpleDateFormat("yyyy-MM-dd").parse(ano + "-" + mes + "-" + dia));
        System.out.println(result);

        return result;
    }

    public List<Consulta> findByDocId(Long id) {
        Optional<Doctor> doc = docRepo.findById(id);

        if (doc.isPresent()) {
            return repository.findByDoctor(doc.get());
        } else {
            throw new EntityNotFoundException("Doc not found " + id);
        }
    }

    public Consulta deleteById(Long id) {
        if (repository.count() == 0) {
            throw new EntityNotFoundException("Repositório vazio!");
        } else {
            Optional<Consulta> consultaAux = repository.findById(id);
            if (consultaAux.isPresent()) {
                repository.deleteById(id);
                return consultaAux.get();
            } else {
                throw new EntityNotFoundException("Usuário não encontrado!");
            }
        }
    }

    public Consulta createConsulta(ConsultaMod consulta) throws ParseException {
        Consulta consultaAux = new Consulta();
        String ano = consulta.date.ano;
        String mes = consulta.date.mes;
        String dia = consulta.date.dia;
        consultaAux.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(ano + "-" + mes + "-" + dia));
        consultaAux.setDoctor(docService.findDocById(consulta.doctor_id));
        consultaAux.setPatient(userService.findUserById(consulta.patient_id));

        return repository.save(consultaAux);

    }

}
