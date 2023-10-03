package com.setxpro.rinha.controllers;

import com.setxpro.rinha.domain.dtos.StackDTO;
import com.setxpro.rinha.domain.entities.Person;
import com.setxpro.rinha.domain.dtos.PersonDTO;
import com.setxpro.rinha.domain.repositories.PersonRepository;
import com.setxpro.rinha.domain.repositories.StackRepository;
import com.setxpro.rinha.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    StackRepository stackRepository;

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody @Valid PersonDTO personDTO, StackDTO stackDTO) throws Exception {
        personService.registerPerson(personDTO, stackDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersonByStack(@RequestParam("t") String t) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findByUserParams(t));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> findAllPersons(@PathVariable(value="id") UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Object> countUsers() throws Exception {

        Number countPersons = personService.findAllPersons();

        CountResponse response = new CountResponse(countPersons);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    private static class CountResponse {
        private Number totalPersons;

        public CountResponse(Number totalPersons) {
            this.totalPersons = totalPersons;
        }
        public Number getTotalPersons() {
            return totalPersons;
        }

        public void setTotalPersons(Number totalPersons) {
            this.totalPersons = totalPersons;
        }
    }
}
