package com.alxmcr.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alxmcr.entities.Person;
import com.alxmcr.services.PersonService;
import com.alxmcr.services.PersonServiceImpl;
import com.google.gson.Gson;

@WebServlet(name = "PersonServlet", urlPatterns = "/person")
public class PersonServlet extends HttpServlet {

	// EntityManager
	private EntityManagerFactory emf;
	private EntityManager em;

	// Service
	private PersonService servicePerson;

	@Override
	public void init() throws ServletException {
		// this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");

		// this.em = emf.createEntityManager();

		// Service
		this.servicePerson = new PersonServiceImpl(this.em);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// List<Person> people = this.servicePerson.getAllPeople();

		String pathInfo = req.getPathInfo();
		System.out.println("pathInfo: " + pathInfo);

		Person p1 = new Person();
		p1.setId(new Long(1));
		p1.setName("a1");
		p1.setLastname("a2");

		Person p2 = new Person();
		p2.setId(new Long(2));
		p2.setName("b1");
		p2.setLastname("b2");

		Person p3 = new Person();
		p3.setId(new Long(3));
		p3.setName("c1");
		p3.setLastname("c2");

		List<Person> people = new ArrayList<Person>();
		people.add(p1);
		people.add(p2);
		people.add(p3);

		// GSON: ArrayList to String
		Gson gson = new Gson();
		String arrayDataStr = gson.toJson(people);
		System.out.println(arrayDataStr);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(arrayDataStr);
		out.flush();
	}

}
