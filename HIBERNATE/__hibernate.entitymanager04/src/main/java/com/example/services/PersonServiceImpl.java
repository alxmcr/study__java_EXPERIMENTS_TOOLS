package com.example.services;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.example.dao.PersonDAOImpl;
import com.example.entities.Person;

@Transactional
public class PersonServiceImpl implements PersonService{

    private PersonDAOImpl dao;

    public PersonServiceImpl(EntityManager em) {
        // DAO
        this.dao = new PersonDAOImpl(em);
    }

    @Override
    public Person savePerson(Person p) throws Exception {
        return this.dao.save(p);
    }

    @Override
    public Person savePerson(Person p, boolean isRollback) throws Exception {
        return this.dao.save(p, isRollback);
    }
}