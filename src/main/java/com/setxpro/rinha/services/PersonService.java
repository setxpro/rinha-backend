package com.setxpro.rinha.services;

import com.setxpro.rinha.domain.dtos.PersonDTO;
import com.setxpro.rinha.domain.dtos.StackDTO;
import com.setxpro.rinha.domain.entities.Person;
import com.setxpro.rinha.domain.entities.Stack;
import com.setxpro.rinha.domain.repositories.PersonRepository;
import com.setxpro.rinha.domain.repositories.StackRepository;
import com.setxpro.rinha.infra.NameExists;
import com.setxpro.rinha.infra.NickNameExists;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final StackRepository stackRepository;

    public PersonService(PersonRepository personRepository, StackRepository stackRepository) throws Exception {
        this.personRepository = personRepository;
        this.stackRepository = stackRepository;
    }

    public void registerPerson(PersonDTO personDTO, StackDTO stackDTO) throws Exception {


        if (personRepository.existsByName(personDTO.name())) {
            throw new NameExists();
        }

        if (personRepository.existsByNickname(personDTO.nickname())) {
            throw new NickNameExists();
        }


        Person person = new Person(personDTO);

        BeanUtils.copyProperties(personDTO, person);

        List<String> stacks = person.getStack();

        UUID uuid = UUID.randomUUID();

        for (String stackName : stacks) {
            Stack stack = new Stack(stackDTO);
            BeanUtils.copyProperties(stackDTO, stack);
            stack.setName(stackName);
            stack.setPersonId(uuid);
            stackRepository.save(stack);
        }
        person.setId(uuid);

        personRepository.save(person);
    }

    public Number findAllPersons() {
        return personRepository.findAll().size();
    }
}
