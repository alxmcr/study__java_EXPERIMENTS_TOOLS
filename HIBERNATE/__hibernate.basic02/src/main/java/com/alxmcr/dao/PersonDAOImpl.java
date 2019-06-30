package com.alxmcr.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.alxmcr.entities.Person;
import com.alxmcr.util.HibernateUtil;

public class PersonDAOImpl implements PersonDAO {

	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	@Override
	public void setUp() {
		// Hibernate configuration
		HibernateUtil hu = new HibernateUtil();
		hu.setup();

		// Session
		factory = hu.getFactory();
	}

	@Override
	public Person findPersonById(int id) {
		Person p = null;

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			p = session.get(Person.class, id);

			// Commit transaction
			tx.commit();

			// Close the session
			session.close();
		}

		return p;
	}

	@Override
	public int createPerson(Person person) {
		
		int id = -1;
		
		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			session.save(person);

			// flush a batch of inserts and release memory:
			session.flush();
			session.clear();

			// Commit transaction
			tx.commit();

			// Close the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (person != null) {
			id = person.getId();
		}

		return id;
	}

	@Override
	public List<Person> readAllPeople() {
		List<Person> people = null;

		try (Session session = factory.openSession()) {

			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			people = session.createQuery("from Person", Person.class).list();

			// Commit transaction
			tx.commit();

			// Close the session
			session.close();
		}

		return people;
	}

	@Override
	public boolean updatePersonName(int idPerson, String name) {
		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			Person person = this.findPersonById(idPerson);
			if (person != null) {
				person.setName(name);

				session.update(person);

				// Commit transaction
				tx.commit();
			}

			// Close the session
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deletePerson(int id, boolean flagRollback) {
		boolean result = false;

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			Person personToDelete = findPersonById(id);

			if (personToDelete != null) {
				session.delete(personToDelete);

				if (flagRollback) {
					tx.rollback();
				} else {
					// Commit transaction
					tx.commit();
				}
				result = true;
			} else {
				System.err.println("Person can not found...");
				result = false;
			}

			// Close the session
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			result = false;
		}
		return result;
	}

}
