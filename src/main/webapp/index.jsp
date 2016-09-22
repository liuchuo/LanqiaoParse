<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.SortedSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
				value="<c:out value='${school}' />" /> <c:out value="${school}" /></label> <br />
		</c:forEach>
		<input type="submit" value="Submit" title="submit" />
	</form>
</body>
</html>
