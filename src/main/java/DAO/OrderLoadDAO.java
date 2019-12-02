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
           // orderLoadBeanList.clear();

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
                orderLoadBeanList.add(order);
                OrderLoadBean.setOlb(order);

                resultSet2 = stmt.executeQuery("SELECT\n" +
                        "Dishes.name as ' dishName'\n" +
                        ", amount\n" +
                        ", sum\n" +
                        "FROM OrderDish\n" +
                        "INNER JOIN Dishes ON Dishes.name = OrderDish.dishName\n" +
                        "WHERE orderId ='"+ id +"'");
                while (resultSet2.next()) {
                    OrderDetailsBean odb = new OrderDetailsBean(id, resultSet2.getString("dishName"),resultSet2.getInt("amount"), resultSet2.getDouble("sum"));
                    OrderDetailsBean.setOdb(odb);
                }
            }

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return orderLoadBeanList;
    }
}
