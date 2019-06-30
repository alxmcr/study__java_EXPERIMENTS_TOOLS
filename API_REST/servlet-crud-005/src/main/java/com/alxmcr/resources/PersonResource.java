package com.alxmcr.resources;

import javax.ws.rs.core.Response;

import com.alxmcr.entities.Person;

public interface PersonResource {
	Response getAllPeople();

	Response getPersonById(String id);

	Response getPersonByName(String name);

	Response savePerson(Person p) throws Exception;
}
