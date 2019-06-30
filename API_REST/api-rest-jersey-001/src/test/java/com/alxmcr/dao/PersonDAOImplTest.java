package com.alxmcr.dao;

import com.alxmcr.entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PersonDAOImplTest {

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
    void findById() {
        this.em = emf.createEntityManager();

        // Operation
        PersonDAOImpl personDAO = new PersonDAOImpl(this.em);
        Person person = personDAO.findById(new Long(1));

        assertNotNull(person);

    }

    @Test
    void findByName() {
        this.em = emf.createEntityManager();

        // Operation
        PersonDAOImpl personDAO = new PersonDAOImpl(this.em);
        Person person = personDAO.findByName("A");

        assertNotNull(person);

    }

    @Test
    void getAll() {
        this.em = emf.createEntityManager();

        // Operation
        PersonDAOImpl personDAO = new PersonDAOImpl(this.em);
        List<Person> people = personDAO.getAll();

        assertNotNull(people);
    }

    @Test
    @Transactional
    void save() {
        this.em = emf.createEntityManager();

        // Operation
        PersonDAOImpl personDAO = new PersonDAOImpl(this.em);

        // New person
        Person person = new Person();
        person.setName("Juan");
        person.setLastname("Dantes");

        Person pe = null;
        try {
            pe = personDAO.save(person);
            assertNotNull(pe);
            assertTrue(pe.getId() > 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}