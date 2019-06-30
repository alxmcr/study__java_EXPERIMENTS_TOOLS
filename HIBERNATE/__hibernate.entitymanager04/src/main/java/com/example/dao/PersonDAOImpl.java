package com.example.dao;

import javax.persistence.EntityManager;

import com.example.entities.Person;

public class PersonDAOImpl implements Dao<Person> {

    private EntityManager entityManager;

    public PersonDAOImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Person save(Person person) throws Exception {

        try {
            entityManager.getTransaction().begin();

            if (person.getId() == null) {
                entityManager.persist(person);
            }

            entityManager.persist(person);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }

        return person;
    }

    @Override
    public Person save(Person person, boolean isRollback) throws Exception {

        try {
            entityManager.getTransaction().begin();

            if (isRollback){
                entityManager.getTransaction().setRollbackOnly();
            }

            if (person.getId() == null) {
                entityManager.persist(person);
            }

            if (entityManager.getTransaction().getRollbackOnly()){
                System.out.println("Rollback...");
                entityManager.getTransaction().rollback();
            } else {
                entityManager.getTransaction().commit();
            }

        } catch (Exception e) {
            if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }

        return person;
    }
}