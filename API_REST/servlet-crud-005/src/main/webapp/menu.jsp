<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<header class="mdl-layout__header">
	<span class="mdl-layout-title">Personal Stuff Management System</span>
	<div class="mdl-layout-spacer"></div>

	<h3>Number Format:</h3>
	<c:set var="now" value="<%=new java.util.Date()%>" />

	<p>
		Formatted Date (1):
		<fmt:formatDate type="time" value="${now}" />
	</p>

</header>