package com.example.services;

import com.example.entities.Person;

public interface PersonService {
    Person savePerson(Person p) throws Exception;
    Person savePerson(Person p, boolean isRollback) throws Exception;
}
