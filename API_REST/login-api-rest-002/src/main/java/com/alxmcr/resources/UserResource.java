package com.alxmcr.resources;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.alxmcr.entities.User;
import com.alxmcr.errors.ErrorApp;
import com.alxmcr.services.UserService;
import com.google.gson.Gson;

@Path("/users")
public class UserResource implements Resource<User> {

	private UserService userService = new UserService();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User t) throws Exception {
		System.out.println("\nlogin()...");

		Response response = null;

		try {
			System.out.println("User" + t);
			// data
			String username = t.getUsername();
			String password = t.getPassword();

			User person = userService.login(username, password);

			response = Response.status(Status.OK).entity(person).build();
		} catch (NoResultException e) {
			String message = e.getMessage();

			ErrorApp error = new ErrorApp();
			error.setMessage(message);

			Gson gson = new Gson();

			response = Response.status(Status.NOT_FOUND).entity(gson.toJson(error)).build();
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return response;
	}

}
