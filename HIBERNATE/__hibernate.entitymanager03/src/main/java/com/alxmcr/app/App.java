package com.alxmcr.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.alxmcr.entities.Person;
import com.alxmcr.util.HibernateUtil;

public class App {

	private static SessionFactory factory;

	public static void main(String[] args) {

		// Hibernate configuration
		HibernateUtil hu = new HibernateUtil();
		hu.setup();

		// Session
		factory = hu.getFactory();

		// Find a person by Id
		findPersonById(1);

		// Find ALL People
		findAllPeople();

		// New Person
		Person per1 = new Person();
		per1.setName("Maria");
		per1.setLastname("Torrez");

		savePerson(per1);

		// Find ALL People
		findAllPeople();

	}

	private static void savePerson(Person person) {
		System.out.println("savePerson....");

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			session.save(person);

			// Commit transaction
			tx.commit();
		}
	}

	private static void findPersonById(int id) {
		System.out.println("findPersonById....");

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			Person p = session.get(Person.class, id);
			System.out.println(p);

			// Commit transaction
			tx.commit();
		}

	}

	public static void findAllPeople() {
		System.out.println("findAllPeople....");

		try (Session session = factory.openSession()) {

			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			List<Person> list = session.createQuery("from Person", Person.class).list();
			for (Person m : list) {
				System.out.println(m);
			}

			// Commit transaction
			tx.commit();
		}
	}

}