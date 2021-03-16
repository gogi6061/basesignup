<%--
  Created by IntelliJ IDEA.
  User: steam
  Date: 05.03.2021
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../css/mathCss/CalcForm.css" rel="stylesheet" type="text/css">
    <title>Число Фибоначи</title>
</head>
<body>
<form class="form-style-1" method="get">
    <label>Веди порядковый номер числа
        <input name="a" type="text" placeholder="a">
        <p>Ответ: ${fib} </p>

        <p><a href="/sumInts">Калькулятор</a>

            <a href="/homepage">Домашняя страница</a></p>
    </label>

    <p><input type="submit"></p>
</form>


</body>
</html>
