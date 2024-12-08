<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>list</title>
</head>
<body>

<table>
    <thead>
    <tr>
        <th>#</th>
        <th>userID</th>
        <th>name</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:foreach items="${users}" var="user" carStatus="status">
        <td>
            <th scope="row">${status.count}</th>
            <td>${user.userId}</td>
            <td>${user.name}</td>
            <td></td>
        </tr>
    </c:foreach>
    </tbody>
</table>
</body>
</html>