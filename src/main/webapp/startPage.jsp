<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <style>
        a.button {
            -webkit-appearance: button;
            -moz-appearance: button;
            appearance: button;

            text-decoration: none;
            color: initial;
            padding: 3px 10px;
        }
        div.buttons {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="buttons">
    <a onclick="location.href='/products'" href="DemoServlet" class="button">Products</a>
    <a onclick="location.href='/dishes'" href="DishesServlet" class="button">Dishes</a>
    <a onclick="location.href='/orderDishes'" href="OrderDishesServlet" class="button">Order Dishes</a>
    <a onclick="location.href='/orders'" href="OrdersServlet" class="button">Orders</a>
    <a onclick="location.href='/receipts'" href="RecipeServlet" class="button">Receipts</a>
    <a onclick="location.href='/staff'" href="StaffServlet" class="button">Staff</a>
</div>
    <div style="text-align: right"><a href="LogoutServlet">Logout</a></div>
</body>
</html>
