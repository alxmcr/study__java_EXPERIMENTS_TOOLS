<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Hola!</h1>
	<ul>
		<%
			List<String> products = (ArrayList<String>) request.getAttribute("products");

			if (products != null) {
				for (String product : products) {
		%>
		<li><%=product%></li>
		<%
			}
		%>
		<%
			}
		%>
	</ul>
</body>
</html>