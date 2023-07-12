package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ConsultaMod;
import com.example.demo.dto.PersonalDate;
import com.example.demo.entities.Consulta;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.User;
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

    public List<Consulta> setPasswordToNull(List<Consulta> lista) {
        List<Consulta> listaAux = new ArrayList();
        for (Consulta consulta : lista) {
            User userAux = consulta.getPatient();
            userAux.setPassword(null);
            consulta.setPatient(userAux);
            listaAux.add(consulta);
        }
        return listaAux;
    }

    public List<Consulta> findAll() {
        return setPasswordToNull(repository.findAll());
    }

    public Consulta findByID(Long id) {
        return repository.findById(id).get();
    }

    public List<Consulta> findByDocId(Long id) {
        Optional<Doctor> doc = docRepo.findById(id);

        if (doc.isPresent()) {
            List<Consulta> lista = repository.findByDoctor(doc.get());

            return setPasswordToNull(lista);
        } else {
            throw new EntityNotFoundException("Doc not found " + id);
        }
    }

    public List<Consulta> findByUserId(UUID id) {
        User user = userService.findUserById(id);

        if (user != null) {
            List<Consulta> lista = repository.findByPatient(user);

            return setPasswordToNull(lista);
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
                User userAux = consultaAux.get().getPatient();
                userAux.setPassword(null);
                consultaAux.get().setPatient(userAux);
                return consultaAux.get();
            } else {
                throw new EntityNotFoundException("Consulta não encontrada!");
            }
        }
    }

    public Boolean confirmConsulta(Long id) {
        if (repository.count() == 0) {
            throw new EntityNotFoundException("Repositório vazio!");
        } else {
            Consulta consultaAux = findByID(id);
            consultaAux.setIsConfirmed(true);
            repository.save(consultaAux);
            return true;
        }
    }

    public Consulta createConsulta(ConsultaMod consulta) throws ParseException {
        Consulta consultaAux = new Consulta();
        String ano = consulta.date.ano;
        Integer aux = Integer.parseInt(consulta.date.mes) + 1;
        String mes = aux.toString();

        String dia = consulta.date.dia;
        String horario = consulta.date.horario;

        consultaAux.setDate(
                new SimpleDateFormat("yyyy/MM/dd HH:mm")
                        .parse(ano + "/" + mes + "/" + dia + " " + horario));

        consultaAux.setIsConfirmed(false);
        consultaAux.setDoctor(docService.findDocById(consulta.doctor_id));
        consultaAux.setPatient(userService.findUserById(consulta.patient_id));

        return repository.save(consultaAux);

    }

    public List<Consulta> findByDay(PersonalDate date) throws ParseException {
        String ano = date.ano;
        String mes = date.mes;
        String dia = date.dia;
        List<Consulta> result = repository.findAllWithCreationDateTimeBefore(
                new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ano + "-" + mes + "-" + dia + " 23:59"));
        List<Consulta> resultAux = new ArrayList();
        for (Consulta consulta : result) {
            System.out.println(consulta.getDate());
            String dateTimeString = consulta.getDate().toString();
            String dateString = dateTimeString.split(" ")[0];
            String[] dateAux = dateString.split("-");

            if (dateAux[0].equals(ano) && dateAux[1].equals(mes)
                    && dateAux[2].equals(dia)) {

                resultAux.add(consulta);
            }
        }

        return resultAux;
    }

}
