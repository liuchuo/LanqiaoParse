<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>LanQiao Parse</title>
</head>
<body>
    <jsp:useBean id="school" scope="request" beanName="schools" type="java.util.Set"/>
    <form method="get" action="download">
        <c:forEach items="${school}" var="s">
            <input name="schoolName" type="checkbox" value="<c:out value='${s}'/> " > <c:out value='${s}'/> <br/>
        </c:forEach>
        <input type="submit">
    </form>
</body>
</html>
