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
    private OrderDetailsBean orderDetailsBean = new OrderDetailsBean();
    private ObservableList<OrderDetailsBean> odbList = FXCollections.observableArrayList();

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

    public void setOrderDishName(String dishName){
        orderDetailsBean.setDishName(dishName);
    }
    public void setOrderDishAmount(int amount){
        orderDetailsBean.setAmount(amount);
       // this.odbList.add(orderDetailsBean);
    }
    public void setOrderDishPrice(Double dishPrice){
        orderDetailsBean.setDishPrice(dishPrice);
        this.odbList.add(orderDetailsBean);
    }

    public ObservableList<OrderDetailsBean> getOrderDetails(){
        return odbList;
    }



}
