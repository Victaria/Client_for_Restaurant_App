<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Orders</title>
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
    <a href="DemoServlet" class="button">Products</a>
    <a href="DishesServlet" class="button">Dishes</a>
    <a href="OrderDishesServlet" class="button">Order Dishes</a>
    <a href="OrdersServlet" class="button">Orders</a>
    <a href="RecipeServlet" class="button">Receipts</a>
    <a href="StaffServlet" class="button">Staff</a>
    <a onclick="location.href='/addOrder'" href="OrderMakeServlet" class="button">Make Order</a>
    <a onclick="location.href='/showOrders'" href="ShowOrdersServlet" class="button selected">My Orders</a>
</div>
<div style="text-align: right"><a href="LogoutServlet">Logout</a></div>
<br>
<table>
    <tr>
        <th>Table</th>
        <th>Date</th>
        <th>Sum</th>
        <th>Staff</th>
    </tr>
    <c:forEach var="col" items="${col}"  >
        <tr>
            <td>${col.table}</td>
            <td>${col.date}</td>
            <td>${col.sum}</td>
            <td>${col.staffName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
