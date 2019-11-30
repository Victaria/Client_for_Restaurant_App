package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderMakeDao {

    public String createOrder(OrderMakeBean orderMakeBean){
        int userId = orderMakeBean.getUserId();
        int table = orderMakeBean.getTable();
        Date date = orderMakeBean.getDate();
        double sum = orderMakeBean.getSum();
        int orderId = orderMakeBean.getOrderId();
        ArrayList<String> dishName = orderMakeBean.getDishName();
        ArrayList<Integer> amount = orderMakeBean.getAmount();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try
        {
            con = DBConnection.createConnection();
            String query = "INSERT INTO Orders(id, tableOrder , sum, dateOrder, staffName) VALUES (?,?,?,?, null )"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, table);
            preparedStatement.setDouble(3, sum);
            preparedStatement.setDate(4, (java.sql.Date) date);

            int i= preparedStatement.executeUpdate();

            String query2 = "INSERT INTO OrderDish(amount, dishName, orderId) VALUES (?,?,?)";
            for (int j=0; j < amount.size(); j++){
                preparedStatement = con.prepareStatement(query2);
                preparedStatement.setInt(1, amount.get(j));
                preparedStatement.setString(2, dishName.get(j));
                preparedStatement.setInt(3, orderId);

                preparedStatement.executeUpdate();
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
