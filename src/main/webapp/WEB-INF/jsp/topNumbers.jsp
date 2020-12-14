<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Top Numbers</title>
</head>
<body>

    <c:forEach var="number" items="${numbers}">
        <p> <c:out value="${number.getNumber()}"/></p>
    </c:forEach>

</body>
</html>