package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OrderLoadBean {
    int userId;
    int table;
    String date;
    double sum;
    int orderId;
    String staffName;
    private static ObservableList<OrderLoadBean> olbList = FXCollections.observableArrayList();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
    }

    public static ObservableList<OrderLoadBean> getOlb() {
        return olbList;
    }

    public static void setOlb(OrderLoadBean olb) {
        olbList.add(olb);
    }
}
