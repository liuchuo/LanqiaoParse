<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.SortedSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8" />
<title>lanqiao Parse</title>
</head>
<body>
	<h1>lanqiao Parse for BinJiang</h1>
	<h4>Select schools which you want to parse:</h4>
	<form action="download" method="get">
		<c:forEach var="school" items="${sessionScope.schools}">
			<label><input type="checkbox" title="school" name="school"
				value="<c:out value='${school}' />" /> <c:out value="${school}" /></label>
		</c:forEach>
		<input type="submit" value="Submit" title="submit" />
	</form>
	<sf:form method="GET">
		<c:forEach var="school" items="${sessionScope.schools}">
			<sf:label path="">
				<sf:input path="<c:out value='${school}' />" />
				<c:out value='${school}' />
			</sf:label>
			<br />
		</c:forEach>
	</sf:form>
</body>
</html>
