<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<span class="mdl-layout-title">Personal Stuff Management System</span>
	<div class="mdl-layout-spacer"></div>

	<h3>Number Format:</h3>
	<c:set var="now" value="<%=new java.util.Date()%>" />

	<p>
		Formatted Date (1):
		<fmt:formatDate type="time" value="${now}" />
	</p>

	<c:forEach var="i" begin="1" end="5">
         Item <c:out value="${i}" />
		<p>
	</c:forEach>

</body>
</html>