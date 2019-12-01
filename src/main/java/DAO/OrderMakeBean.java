package DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class OrderMakeBean {
    int userId;
    int table;
    String date;
    double sum;
    int orderId;
    ArrayList<String> dishName;
    ArrayList<Integer> amount;
    String staffName;

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

    public ArrayList<String> getDishName() {
        return dishName;
    }

    public void setDishName(ArrayList<String> dishName) {
        this.dishName = dishName;
    }

    public ArrayList<Integer> getAmount() {
        return amount;
    }

    public void setAmount(ArrayList<Integer> amount) {
        this.amount = amount;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
    }
}
