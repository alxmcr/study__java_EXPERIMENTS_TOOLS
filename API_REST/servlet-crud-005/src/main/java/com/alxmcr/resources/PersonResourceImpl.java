package com.alxmcr.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alxmcr.entities.Person;
import com.alxmcr.services.PersonService;

@Path("/people")
public class PersonResourceImpl implements PersonResource {

	@Inject
	private PersonService personService;

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPeople() {
		List<Person> people = personService.getAllPeople();

		return Response.status(Response.Status.OK).entity(people).build();
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonById(@PathParam("id") Long id) {

		Person person = personService.getPersonById(id);

		return Response.status(Response.Status.OK).entity(person).build();
	}

	@GET
	@Path("/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonByName(String name) {
		Person person = personService.getPersonByName(name);

		return Response.status(Response.Status.OK).entity(person).build();
	}

	public Response savePerson(Person p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Response getPersonById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
