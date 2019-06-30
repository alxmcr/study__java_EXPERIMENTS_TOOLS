package com.alxmcr.services;

import com.alxmcr.dao.PersonDAOImpl;
import com.alxmcr.entities.Person;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PersonServiceImpl implements PersonService{

    private PersonDAOImpl dao;

    public PersonServiceImpl(EntityManager em) {
        // DAO
        this.dao = new PersonDAOImpl(em);
    }

    @Override
    public Person getPersonById(Long id) {
        return this.dao.findById(id);
    }

    @Override
    public Person getPersonByName(String name) {
        return this.dao.findByName(name);
    }

    @Override
    public List<Person> getAllPeople() {
        return this.dao.getAll();
    }

    @Override
    public Person savePerson(Person p) throws Exception {
        return this.dao.save(p);
    }

    @Override
    public Person savePerson(Person p, boolean isRollback) throws Exception {
        return this.dao.save(p, isRollback);
    }

    @Override
    public Person updatePerson(Person p, String[] params) {
        return this.dao.update(p, params);
    }

    @Override
    public Person deletePerson(Person p) {
        return this.dao.delete(p);
    }
}
