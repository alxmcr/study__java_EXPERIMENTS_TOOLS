package com.alxmcr.resources;

import javax.ws.rs.core.Response;

import com.alxmcr.entities.Person;

public interface PersonResource {
	Response getAllPeople();

	Response getPersonById(Long id);

	Response getPersonByName(String name);

	Response savePerson(Person p) throws Exception;

	Response deletePerson(Long id) throws Exception;

	Response updatePerson(Long id, Person personUpdated) throws Exception;

}
