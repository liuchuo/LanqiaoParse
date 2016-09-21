<%@page import="java.util.SortedSet"%>
<html>
<head>
	<meta charset="utf-8">
</head>
<body>
	<h1>lanqiao Parse for BinJiang</h1>
	<h4>Select schools which you want to parse:</h4>
	<form action="/download" method="get">
		<c:forEach items="${sessionScope.schools}" var="school">
			<input type="checkbox" value="${school}" />
		</c:forEach>
		<input type="submit" value="parse" />
	</form>
</body>
</html>
