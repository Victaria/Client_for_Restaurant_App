<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Order info</title>
<meta charset="UTF-8">
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
<h1>Заказ № ${id}</h1>
<span>Столик: ${table}</span><br>
<span>Официант: ${staffName}</span><br>
<span>Дата заказа: ${date}</span><br><br>
<table>
    <tr>
        <th style="width: 70%">Блюдо</th>
        <th>Количество</th>
        <th>Сумма</th>
    </tr>
<c:forEach var="col" items="${col}"  >
    <tr>
        <td>${col.dishName}</td>
        <td>${col.amount}</td>
        <td>${col.dishPrice}</td>
    </tr>
</c:forEach>
    <tr>
        <th colspan="2" style="text-align: right">Сумма</th>
        <th>${sum}</th>
    </tr>
</table>
</body>

</html>
