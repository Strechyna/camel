package com.example.camel.services;

import com.example.camel.records.Person;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private static final Map<Integer, Person> PERSON_MAP = new HashMap<>();

    @Override
    public Person findById(Integer id) {
        return PERSON_MAP.get(id);
    }

    @Override
    public Collection<Person> findAll() {
        return PERSON_MAP.values();
    }

    @Override
    public void update(Person person) {
        PERSON_MAP.put(person.id(), person);
    }
}
