package com.alxmcr.services;

import com.alxmcr.entities.Person;

import java.util.List;

public interface PersonService {
    Person getPersonById(Long id);

    Person getPersonByName(String name);

    List<Person> getAllPeople();

    Person savePerson(Person p) throws Exception;

    Person savePerson(Person p, boolean isRollback) throws Exception;

    Person updatePerson(Person p, String[] params);

    Person deletePerson(Person p);
}
