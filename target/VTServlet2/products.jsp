<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Products</title>
    <style>
        a.button {
            -webkit-appearance: button;
            -moz-appearance: button;
            appearance: button;

            text-decoration: none;
            color: initial;
            padding: 3px 10px;
        }
        a.selected {
            color: blue;
        }
        div.buttons {
            width: 100%;
            text-align: center;
        }
        table, td, th {
            border: 1px solid black;
        }
        th{
            padding: 10px;
            text-align: center;
        }
        td {
            padding: 3px 5px;
        }
        table{
            width: 100%;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<div class="buttons">
    <a href="DemoServlet" class="button selected">Products</a>
    <a href="DishesServlet" class="button">Dishes</a>
    <a href="OrderDishesServlet" class="button">Order Dishes</a>
    <a href="OrdersServlet" class="button">Orders</a>
    <a href="RecipeServlet" class="button">Receipts</a>
    <a href="StaffServlet" class="button">Staff</a>
    <a onclick="location.href='/addOrder'" href="OrderMakeServlet" class="button">Make Order</a>
    <a onclick="location.href='/showOrders'" href="ShowOrdersServlet" class="button">My Orders</a>
</div>
<div style="text-align: right"><a href="LogoutServlet">Logout</a></div>
<br>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Amount</th>
    </tr>
    <c:forEach var="col" items="${col}"  >
        <tr>
            <td>${col.id}</td>
            <td>${col.name}</td>
            <td>${col.price}</td>
            <td>${col.amount}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
