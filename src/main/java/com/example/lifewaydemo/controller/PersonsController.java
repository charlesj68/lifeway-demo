package com.example.lifewaydemo.controller;

import com.example.lifewaydemo.dto.PersonDto;
import com.example.lifewaydemo.model.Person;
import com.example.lifewaydemo.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonsController {

    PersonRepo repo;

    @Autowired
    PersonsController(PersonRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        try {
            List<Person> persons = repo.findAll();

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        Optional<Person> person = repo.findById(id);

        return person.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto person) {
        try {
            Person newPerson = repo.save(new Person(person.getFirstName(), person.getLastName(), person.getEmail(), person.getPhone()));
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody PersonDto person) {
        Optional<Person> personData = repo.findById(id);

        if (personData.isPresent()) {
            Person modPerson = personData.get();
            modPerson.setFirstName(person.getFirstName());
            modPerson.setLastName(person.getLastName());
            modPerson.setEmail(person.getEmail());
            modPerson.setPhone(person.getPhone());
            return new ResponseEntity<>(repo.save(modPerson), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable("id") long id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
