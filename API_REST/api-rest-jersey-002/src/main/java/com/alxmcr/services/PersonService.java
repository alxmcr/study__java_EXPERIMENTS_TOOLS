package com.alxmcr.services;

import com.alxmcr.entities.Person;

import java.util.List;

public interface PersonService {
	Person getPersonById(Long id);

	Person getPersonByName(String name);

	List<Person> getAllPeople();

	Person savePerson(Person p);

	Person savePerson(Person p, boolean isRollback);

	Person updatePerson(Long id, Person personUpdated);

	Person deletePerson(Long id);
}
