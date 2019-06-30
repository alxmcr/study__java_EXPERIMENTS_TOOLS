package com.alxmcr.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.alxmcr.dao.PersonDAOImpl;
import com.alxmcr.entities.Person;

class PersonDAOTest {

	private static PersonDAOImpl personDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		personDAO = new PersonDAOImpl();
		personDAO.setUp();
	}

	/**
	 * Test method for {@link com.alxmcr.dao.PersonDAOImpl#setup()}.
	 */
	@Test
	void testSetup() {

		SessionFactory factory = personDAO.getFactory();

		assertNotNull(factory);
	}

	/**
	 * Test method for {@link com.alxmcr.dao.PersonDAOImpl#findPersonById(int)}.
	 */
	@Test
	void testFindPersonById() {

		// Person List
		List<Person> people = personDAO.readAllPeople();

		if (people != null) {
			if (!people.isEmpty()) {
				// ID Person
				int idPerson = people.get(people.size() - 1).getId();

				Person person = personDAO.findPersonById(idPerson);

				assertNotNull(person);
				assertEquals(idPerson, person.getId());
				assertEquals("Mike", person.getName());
				assertEquals("E", person.getLastname());
			} else {
				System.err.println("List is empty...");
			}
		} else {
			assertFalse(false, "List is empty...");
		}

	}

	/**
	 * Test method for
	 * {@link com.alxmcr.dao.PersonDAOImpl#createPerson(com.alxmcr.entities.Person)}.
	 */
	@Test
	void testCreatePerson() {

		// New Person
		String name = "David";
		String lastname = "Dominguez";

		Person person = new Person();
		person.setName(name);
		person.setLastname(lastname);

		int id = personDAO.createPerson(person);
		assertTrue(id > 0);
		assertNotNull(id);
		assertEquals(name, person.getName());
		assertEquals(lastname, person.getLastname());

		boolean isRollback = false;
		boolean isDeleted = personDAO.deletePerson(id, isRollback);
		assertTrue(isDeleted);
	}

	/**
	 * Test method for {@link com.alxmcr.dao.PersonDAOImpl#readAllPeople()}.
	 */
	@Test
	void testReadAllPeople() {
		List<Person> people = personDAO.readAllPeople();
		assertNotNull(people);
	}

	@Test
	void updatePersonName() {

		// Person List
		List<Person> people = personDAO.readAllPeople();

		if (people != null) {
			if (!people.isEmpty()) {
				// ID Person
				int idPerson = people.get(people.size() - 1).getId();

				String name = "Mike";
				boolean isUpdated = personDAO.updatePersonName(idPerson, name);
				assertTrue(isUpdated);

				Person person = personDAO.findPersonById(idPerson);

				assertNotNull(person);
				assertEquals(idPerson, person.getId());
				assertEquals(name, person.getName());
			} else {
				System.err.println("List is empty...");
			}
		} else {
			assertFalse(false, "List is empty...");
		}

	}

	@Test
	void testDeletePerson() {
		// Person List
		List<Person> people = personDAO.readAllPeople();

		if (people != null) {
			if (!people.isEmpty()) {
				// ID Person
				int idPerson = people.get(0).getId();

				// Delete
				boolean isRollback = false;
				boolean isDeleted = personDAO.deletePerson(idPerson, isRollback);
				assertTrue(isDeleted);

				// Check
				Person person = personDAO.findPersonById(idPerson);
				assertNull(person);
			} else {
				System.err.println("List is empty...");
			}
		} else {
			assertFalse(false, "List is empty...");
		}

	}

}
