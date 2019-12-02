package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderLoadDAO {
    private static ObservableList<OrderLoadBean> orderLoadBeanList = FXCollections.observableArrayList();

    public static ObservableList<OrderLoadBean> loadOrder(){
        int id = 0;
        int userId = LoginBean.getStaticId();
        int table = 0;
        String date = "";
        double sum = 0;
        String staffName = "";
        String name;

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        Statement stmt;
        Statement stmt2;
        ResultSet res2Set = null;
        ResultSet resultSet2 = null;

        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement(); //Statement is used to write queries. Read more about it.
            stmt = con.createStatement();
            stmt2 = con.createStatement();

            resultSet = statement.executeQuery("SELECT * from Orders WHERE userID='"+ userId +"'");
            while (resultSet.next()){
                OrderLoadBean order = new OrderLoadBean();

                id = Integer.parseInt(resultSet.getString("id"));
                table = Integer.parseInt(resultSet.getString("tableOrder"));
                sum = Double.parseDouble(resultSet.getString("sum"));
                date = resultSet.getString("dateOrder");
                staffName = resultSet.getString("staffName");

                order.setOrderId(id);
                order.setTable(table);
                order.setSum(sum);
                order.setDate(date);
                order.setStaffName(staffName);

                resultSet2 = stmt.executeQuery("SELECT * from OrderDish WHERE orderId='"+ id +"'");
                while (resultSet2.next()) {
                    name = resultSet2.getString("dishName");
                    order.setOrderDishName(name);
                   // dishName.add(resultSet2.getString("dishName"));
                    order.setOrderDishAmount(resultSet2.getInt("amount"));
                    //amount.add(resultSet2.getInt("amount"));

                    res2Set = stmt2.executeQuery("SELECT sum from Dishes WHERE name='" + name + "'");

                    while (res2Set.next()) order.setOrderDishPrice(res2Set.getDouble(1));
                }

                orderLoadBeanList.add(order);
            }

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return orderLoadBeanList;
    }
}
