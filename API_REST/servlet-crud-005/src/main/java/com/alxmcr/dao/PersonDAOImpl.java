package com.alxmcr.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.alxmcr.entities.Person;

public class PersonDAOImpl implements Dao<Person> {

	private EntityManager entityManager;

	public PersonDAOImpl(EntityManager em) {
		this.entityManager = em;
	}

	public Person findById(Long id) {
		return entityManager.find(Person.class, id);
	}

	public Person findByName(String name) {
		TypedQuery<Person> personByName = entityManager.createQuery("FROM Person WHERE name= :name", Person.class);
		personByName.setParameter("name", name);
		return personByName.getSingleResult();
	}

	public List<Person> getAll() {
		TypedQuery<Person> peopleQuery = entityManager.createQuery("FROM Person", Person.class);
		return peopleQuery.getResultList();
	}

	public Person save(Person person) throws SQLException {

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

	public Person save(Person person, boolean isRollback) throws SQLException {

		try {
			entityManager.getTransaction().begin();

			if (isRollback) {
				entityManager.getTransaction().setRollbackOnly();
			}

			if (person.getId() == null) {
				entityManager.persist(person);
			}

			if (entityManager.getTransaction().getRollbackOnly()) {
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

	public Person update(Person person, String[] params) {
		person.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
		person.setLastname(Objects.requireNonNull(params[1], "Lastname cannot be null"));

		entityManager.merge(person);
		return person;
	}

	public Person delete(Person person) {
		entityManager.remove(person);
		return person;
	}
}
