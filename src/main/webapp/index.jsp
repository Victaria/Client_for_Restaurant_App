<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <style>
        form {
            width: 300px;
            margin: auto;
        }
        input{
            width: 300px;
            margin: 5px auto;
        }
        input[type="text"]{
            width: 294px;
        }
        h2{
            text-align: center;
        }
    </style>
</head>
<body>

<form action="LoginServlet" method="post">
    <h2>Application login</h2>
    E-mail:<br>
    <input type="text" name="email" placeholder="E-mail">
    <br>
    Password:<br>
    <input type="text" name="password" placeholder="Password">
    <br>
    <input type="submit" value="login"><br>
    <a style="font-size: 14px" onclick="location.href='/registration'" href="RegisterServlet">Registration</a>
</form>

</body>
</html>

