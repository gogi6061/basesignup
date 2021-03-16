<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: steam
  Date: 03.03.2021
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <link href="../../css/mathCss/CalcForm.css" rel="stylesheet" type="text/css">
    <title>Калькулятор</title>
</head>
<body>

<form class="form-style-1" method="get">
    <h1>Калькулятор</h1>


    <label>Введи выражение
        в виде обратной польской нотации

    </label>

    <input name="a" type="text" placeholder="пример">

    <p class="btn"><input type="submit" required></p>

    <p>Ответ: ${sum}</p>


    <p><a href="/fibInts">Числа Фибонначи</a>
        <a href="/homepage">Домашняя страничка</a></p>

</form>

<table class="form-style-1">
    <tr>
        <th>Список ответов</th>

    </tr>

    <td>
        <ul>
            <c:forEach var="user" items="${answers}">
                <li><c:out value="${user}"/></li>
            </c:forEach>
        </ul>
    </td>
</table>

<%--<p >Ответ: ${sum}</p>--%>


</body>
</html>
