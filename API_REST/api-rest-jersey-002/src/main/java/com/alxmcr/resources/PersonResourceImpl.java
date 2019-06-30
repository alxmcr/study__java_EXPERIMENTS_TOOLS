package com.alxmcr.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.alxmcr.entities.Person;
import com.alxmcr.services.PersonServiceImpl;

@Path("/people")
public class PersonResourceImpl implements PersonResource {

	private PersonServiceImpl personService = new PersonServiceImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPeople() {
		System.out.println("getAllPeople()...");
		Response response = null;

		try {
			List<Person> people = personService.getAllPeople();

			System.out.println("people: " + people);

			response = Response.status(Response.Status.OK).entity(people).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonById(@PathParam("id") Long id) {
		System.out.println("getPersonById()...");

		Response response = null;

		try {
			Person person = personService.getPersonById(id);

			System.out.println("person: " + person);

			response = Response.status(Response.Status.OK).entity(person).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@GET
	@Path("search/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonByName(@PathParam("name") String name) {
		System.out.println("getPersonByName()...");

		Response response = null;

		try {
			Person person = personService.getPersonByName(name);

			System.out.println("person: " + person);

			response = Response.status(Response.Status.OK).entity(person).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response savePerson(Person p) throws Exception {
		System.out.println("\nsavePerson()...");

		Response response = null;

		try {
			Person person = personService.savePerson(p);

			response = Response.status(Status.CREATED).entity(person).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@DELETE
	@Path("/person/{id}")
	public Response deletePerson(@PathParam("id") Long id) throws Exception {
		System.out.println("deletePerson()...");

		Response response = null;

		try {
			Person person = personService.deletePerson(id);

			System.out.println("person: " + person);

			response = Response.status(Response.Status.OK).entity(person).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@PUT
	@Path("/person/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("id") Long id, Person personUpdated) throws Exception {
		System.out.println("updatePerson()...");

		Response response = null;

		try {
			Person person = personService.updatePerson(id, personUpdated);

			System.out.println("updatePerson: " + person);

			response = Response.status(Response.Status.OK).entity(person).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
