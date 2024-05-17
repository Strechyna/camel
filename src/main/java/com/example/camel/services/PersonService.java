package com.example.camel.services;

import com.example.camel.records.Person;

import java.util.Collection;

public interface PersonService {

    Person findById(Integer id);
    Collection<Person> findAll();
    void update(Person person);
}
