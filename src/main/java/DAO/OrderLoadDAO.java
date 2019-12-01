package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderLoadDAO {
    private static ObservableList<OrderMakeBean> orderMakeBeanList = FXCollections.observableArrayList();

    public static ObservableList<OrderMakeBean> loadOrder(){
        int id = 0;
        int userId = LoginBean.getStaticId();
        int table = 0;
        String date = "";
        double sum = 0;
        ArrayList<String> dishName = new ArrayList<>();
        ArrayList<Integer> amount = new ArrayList<>();
        String staffName = "";

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        Statement stmt;
        ResultSet res2Set = null;
        ResultSet resultSet2 = null;

        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement(); //Statement is used to write queries. Read more about it.
            stmt = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from Orders WHERE userID='"+ userId +"'");
            while (resultSet.next()){
                id = Integer.parseInt(resultSet.getString("id"));
                table = Integer.parseInt(resultSet.getString("tableOrder"));
                sum = Double.parseDouble(resultSet.getString("sum"));
                date = resultSet.getString("dateOrder");
                staffName = resultSet.getString("staffName");

                resultSet2 = stmt.executeQuery("SELECT * from OrderDish WHERE orderId='"+ id +"'");
                while (resultSet2.next()){
                    dishName.add(resultSet2.getString("dishName"));
                    amount.add(resultSet2.getInt("amount"));
                }

                OrderMakeBean order = new OrderMakeBean();
                order.setOrderId(id);
                order.setTable(table);
                order.setSum(sum);
                order.setDate(date);
                order.setDishName(dishName);
                order.setAmount(amount);
                order.setStaffName(staffName);

                orderMakeBeanList.add(order);
            }

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return orderMakeBeanList;
    }
}
