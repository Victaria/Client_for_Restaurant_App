<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
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

<form action="RegisterServlet" method="post">
    <h2>Registration</h2>
    Name:<br>
    <input type="text" name="name" placeholder="Name">
    E-mail:<br>
    <input type="text" name="email" placeholder="E-mail">
    <br>
    Password:<br>
    <input type="text" name="password1" placeholder="Password">
    Repeat password:<br>
    <input type="text" name="password2" placeholder="Repeat password">
    <br>
    <input type="submit" value="Registration"><br>
    <a style="font-size: 14px" href="index.jsp">Do you have account? Login</a>
</form>

</body>
</html>
