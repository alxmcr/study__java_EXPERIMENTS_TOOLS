package com.alxmcr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.alxmcr.entities.Person;

public class PersonDAOImpl implements Dao<Person> {

	private EntityManager entityManager;

	public PersonDAOImpl(EntityManager em) {
		this.entityManager = em;
	}

	@Override
	public Person findById(Long id) throws Exception {
		System.out.println("DAO: findById()");

		Person person = null;

		try {
			person = entityManager.find(Person.class, id);
		} catch (Exception e) {
			throw e;
		}

		return person;
	}

	@Override
	public Person findByName(String name) throws Exception {
		System.out.println("DAO: findByName()");

		Person person = null;

		try {
			TypedQuery<Person> personByName = entityManager.createQuery("FROM Person WHERE name= :name", Person.class);
			personByName.setParameter("name", name);
			person = personByName.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
		return person;
	}

	@Override
	public List<Person> getAll() throws Exception {
		List<Person> people = null;

		try {
			TypedQuery<Person> peopleQuery = entityManager.createQuery("FROM Person", Person.class);
			people = peopleQuery.getResultList();
		} catch (Exception e) {
			throw e;
		}

		return people;
	}

	@Override
	public Person save(Person person) throws Exception {
		System.out.println("DAO: save()");
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
		}

		return person;
	}

	@Override
	public Person save(Person person, boolean isRollback) throws Exception {
		System.out.println("DAO: save()");
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
		}

		return person;
	}

	@Override
	public Person update(Long id, Person personUpdated) throws Exception {
		System.out.println("\n*********************");
		System.out.println("DAO: update()");

		Person currentPerson = null;
		EntityTransaction transaction = null;

		try {
			currentPerson = this.findById(id);

			transaction = entityManager.getTransaction();

			transaction.begin();

			if (currentPerson != null) {

				// Params
				String name = personUpdated.getName();
				String lastname = personUpdated.getLastname();

				if (name != null) {
					currentPerson.setName(name);
				}

				if (lastname != null) {
					currentPerson.setLastname(lastname);
				}

				System.out.println("Person updated:" + currentPerson);

				entityManager.merge(currentPerson);
			} else {
				System.out.println("Person doesn't exist");
			}

			transaction.commit();
		} catch (Exception e) {

			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			throw e;
		}

		return currentPerson;
	}

	@Override
	public Person delete(Long id) throws Exception {
		System.out.println("\n*********************");
		System.out.println("DAO: delete()");

		Person person = null;
		EntityTransaction transaction = null;

		try {
			person = this.findById(id);

			transaction = entityManager.getTransaction();

			transaction.begin();

			if (person != null) {
				entityManager.remove(person);
			} else {
				System.out.println("Person doesn't exist");
			}

			transaction.commit();
		} catch (Exception e) {

			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			throw e;
		}

		return person;
	}
}
