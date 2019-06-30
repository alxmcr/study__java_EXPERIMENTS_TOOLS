<%@ page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>
	<h1>Welcome to example.com</h1>

	<%
		System.out.println("Welcome...");
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement().toString();
			System.out.println("key:" + key);
		}
		String username = String.valueOf(session.getAttribute("username"));
		String password = String.valueOf(session.getAttribute("password"));
		if (username != null && password != null) {
	%>
	<h2>
		Welcome
		<%=username + " " + password%></h2>
	<a href="logout">Logout</a>
	<%
		} else {
			System.out.println("Mal!!!");
		}
	%>
</body>
</html>