package com.alxmcr.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// parameters
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (USERNAME.equals(username) && PASSWORD.equals(password)) {
			System.out.println("Credentials valid...");
			try {
				// SESSION
				HttpSession session = req.getSession(true);

				System.out.println("username: " + username);
				System.out.println("password: " + password);

				session.setAttribute("username", username);
				session.setAttribute("password", password);

				RequestDispatcher rs = req.getRequestDispatcher("welcome.jsp");
				rs.forward(req, resp);

			} catch (Exception e) {
				resp.sendRedirect("index.jsp");
			}
		} else {
			System.err.println("Credentials invalid...");
			resp.sendRedirect("index.jsp");
		}

	}

}
