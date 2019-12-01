package DAO;

import javafx.print.Printer;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class OrderMakeDao {

    public String createOrder(OrderMakeBean orderMakeBean){
        int userId = orderMakeBean.getUserId();
        int table = orderMakeBean.getTable();
        String date = orderMakeBean.getDate();
        double sum = orderMakeBean.getSum();
        int orderId = orderMakeBean.getOrderId();
        ArrayList<String> dishName = orderMakeBean.getDishName();
        ArrayList<Integer> amount = orderMakeBean.getAmount();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        int i = 0;

        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement(); //Statement is used to write queries. Read more about it.
            resultSet = statement.executeQuery("select MAX(id) from Orders");

            while (resultSet.next())
            orderMakeBean.setOrderId(Integer.parseInt(resultSet.getString(1))+1);

            orderId = orderMakeBean.getOrderId();

            String query = "INSERT INTO Orders(id, tableOrder , sum, dateOrder, staffName, userID) VALUES (?,?,?,?, ?, ? )"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, table);
            preparedStatement.setDouble(3, sum);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, "NULL");
            preparedStatement.setInt(6, userId);

            preparedStatement.executeUpdate();

            statement = con.createStatement(); //Statement is used to write queries. Read more about it.
            resultSet = statement.executeQuery("select MAX(id) from OrderDish");

            int orderDishId = 0;
            while (resultSet.next())
                orderDishId = Integer.parseInt(resultSet.getString(1));

            String query2 = "INSERT INTO OrderDish(id, amount, dishName, orderId) VALUES (?,?,?,?)";
            for (int j=0; j < amount.size(); j++){
                preparedStatement = con.prepareStatement(query2);
                preparedStatement.setInt(1, orderDishId + j + 1);
                preparedStatement.setInt(2, amount.get(j));
                preparedStatement.setString(3, dishName.get(j));
                preparedStatement.setInt(4, orderId);

                i =  preparedStatement.executeUpdate();
            }

            if (i!=0)  //Just to ensure data has been inserted into the database
                return "SUCCESS";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
    }
}
