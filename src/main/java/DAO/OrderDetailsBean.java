package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OrderDetailsBean {
    private int id;
    private String dishName;
    private Integer amount;
    private Double dishPrice;
    private static ObservableList<OrderDetailsBean> odbList = FXCollections.observableArrayList();

    public OrderDetailsBean(int id, String dishName, Integer amount, Double dishPrice) {
        this.id = id;
        this.dishName = dishName;
        this.amount = amount;
        this.dishPrice = dishPrice;
    }

    public OrderDetailsBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public static ObservableList<OrderDetailsBean> getOdb() {
        return odbList;
    }

    public static void setOdb(OrderDetailsBean odb) {
        odbList.add(odb);
    }
}
