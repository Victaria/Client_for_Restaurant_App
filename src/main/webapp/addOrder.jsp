<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add order</title>
    <meta charset="UTF-8">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style type="text/css">
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
        div{
            padding:8px;
        }
        #main{
            width: 500px;
            margin: auto;
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
    <a onclick="location.href='/addOrder'" href="OrderMakeServlet" class="button selected">Make Order</a>
    <a onclick="location.href='/showOrders'" href="ShowOrdersServlet" class="button">My Orders</a>
</div>
<div style="text-align: right"><a href="LogoutServlet">Logout</a></div>
<br>

<div id="main">
    <h1 style="text-align: center;">Создание нового заказа</h1>
    <form id='TextBoxesGroup' action="OrderMakeServlet" method="post">
        Номер столика:
        <select id="table" name="table">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
        </select>
        <br><br>
        <div id="TextBoxDiv1">
            <label>1: </label>
            <select name="dishFor1">
            <c:forEach var="col" items="${col}"  >
                <option value="${col.id}">${col.name}</option>
            </c:forEach>
            </select>
            Количество:
            <select name="countFor1">
                <option selected value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
            </select>
        </div>
        <input id="dishesCount" type="hidden" name="dishesCount" value="1">
    </form>
    <input type='button' value='Добавить' id='addButton'>
    <input type='button' value='Удалить' id='removeButton'>
    <br><br>
    <div style="text-align: center;">
        <input style="width: 200px"type="submit" value="Отправить заказ" id="SubmitData">
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function(){

        var counter = 2;

        $("#addButton").click(function () {

            var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv' + counter);

            newTextBoxDiv.after().html('<label>' + counter + ': </label><select name="dishFor'+counter+'"><c:forEach var="col" items="${col}"  ><option value="${col.id}">${col.name}</option></c:forEach> + </select> Количество: <select name="countFor'+counter+'"><option selected value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option></select>');

            newTextBoxDiv.appendTo("#TextBoxesGroup");

            $("#dishesCount").val(counter)

            counter++;
        });

        $("#removeButton").click(function () {
            if(counter == 2){
                alert("Нельзя полностью очистить заказ");
                return false;
            }

            counter--;
            $("#dishesCount").val(counter-1)
            $("#TextBoxDiv" + counter).remove();
        });

        $( "#SubmitData" ).click(function() {
            $( "#TextBoxesGroup" ).submit();
        });
    });
</script>

</body>
</html>
