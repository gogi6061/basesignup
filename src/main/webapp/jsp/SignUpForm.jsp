<%--
  Created by IntelliJ IDEA.
  User: steam
  Date: 11.03.2021
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../css/mathCss/CalcForm.css" rel="stylesheet" type="text/css">

    <title>Регистрация</title>
</head>
<body>
<form class="form-style-1" method="post">
    <label>Login <p><input type="text" placeholder="login" name="login"></p></label>
    <label>Password <p><input type="password" placeholder="password" name="password"></p></label>
    <p>${errString}</p>

    <p><input type="submit"></p>


</form>

</body>
</html>
