package com.alxmcr.dao;

import java.util.List;

import com.alxmcr.entities.Person;

public interface PersonDAO {
	void setUp();

	Person findPersonById(int id);

	int createPerson(Person person);

	List<Person> readAllPeople();

	boolean updatePersonName(int idPerson, String name);

	boolean deletePerson(int id, boolean flagRollback);

}
