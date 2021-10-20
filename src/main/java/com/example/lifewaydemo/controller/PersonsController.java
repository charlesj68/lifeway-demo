package com.example.lifewaydemo.controller;

import com.example.lifewaydemo.dto.PersonCreateDto;
import com.example.lifewaydemo.dto.PersonDto;
import com.example.lifewaydemo.dto.PersonDtoMapper;
import com.example.lifewaydemo.model.Person;
import com.example.lifewaydemo.repository.PersonRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PersonsController {

    PersonRepo repo;

    private final PersonDtoMapper mapper = Mappers.getMapper(PersonDtoMapper.class);

    @Autowired
    PersonsController(PersonRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        try {
            List<Person> persons = repo.findAll();

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<PersonDto> dtoPersons = persons.stream().map(mapper::PersonToPersonDto).collect(Collectors.toList());
            return new ResponseEntity<>(dtoPersons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable("id") long id) {
        Optional<Person> person = repo.findById(id);

        if (person.isPresent()) {
            PersonDto dtoPerson = mapper.PersonToPersonDto(person.get());
            return new ResponseEntity<>(dtoPerson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/persons")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonCreateDto person) {
        try {
            Person newPerson = repo.save(new Person(person.getFirstName(), person.getLastName(), person.getEmail(), person.getPhone()));
            PersonDto dtoPerson = mapper.PersonToPersonDto(newPerson);
            return new ResponseEntity<>(dtoPerson, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable("id") long id, @RequestBody PersonCreateDto person) {
        Optional<Person> personData = repo.findById(id);

        if (personData.isPresent()) {
            Person modPerson = personData.get();
            modPerson.setFirstName(person.getFirstName());
            modPerson.setLastName(person.getLastName());
            modPerson.setEmail(person.getEmail());
            modPerson.setPhone(person.getPhone());
            modPerson = repo.save(modPerson);
            PersonDto dtoPerson = mapper.PersonToPersonDto(modPerson);
            return new ResponseEntity<>(dtoPerson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<PersonDto> deletePerson(@PathVariable("id") long id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
