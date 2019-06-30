package com.alxmcr.services;

import com.alxmcr.entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
class PersonServiceImplTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @AfterEach
    void tearDown() {
        emf.close();
    }

    @Test
    void getPersonById() {
        this.em = emf.createEntityManager();

        // Operation
        PersonServiceImpl personService = new PersonServiceImpl(this.em);

        Person person = personService.getPersonById(new Long(1));
        assertNotNull(person);
    }

    @Test
    void getPersonByName() {
        this.em = emf.createEntityManager();

        // Operation
        PersonServiceImpl personService = new PersonServiceImpl(this.em);

        Person person = personService.getPersonByName("A");
        assertNotNull(person);
    }

    @Test
    void getAllPeople() {
        this.em = emf.createEntityManager();

        // Operation
        PersonServiceImpl personService = new PersonServiceImpl(this.em);

        List<Person> people = personService.getAllPeople();

        assertNotNull(people);
    }

    @Test
    @Transactional
    void savePerson() {
        this.em = emf.createEntityManager();

        // Operation
        PersonServiceImpl personService = new PersonServiceImpl(this.em);

        // New person
        Person person = new Person();
        person.setName("Paul");
        person.setLastname("Yanti");

        Person pe = null;
        try {
            pe = personService.savePerson(person);
            assertNotNull(pe);
            assertTrue(pe.getId() > 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Transactional
    void savePersonRollback() {
        this.em = emf.createEntityManager();

        // Operation
        PersonServiceImpl personService = new PersonServiceImpl(this.em);

        // New person
        Person person = new Person();
        person.setName("Michael");
        person.setLastname("Fisher");

        Person pe = null;
        try {
            pe = personService.savePerson(person, true);
            assertNotNull(pe);
            assertTrue(pe.getId() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }
}