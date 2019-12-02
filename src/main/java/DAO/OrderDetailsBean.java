package DAO;

import java.util.ArrayList;

public class OrderDetailsBean {
    private String dishName;
    private Integer amount;
    private Double dishPrice;

    public OrderDetailsBean(String dishName, Integer amount, Double dishPrice) {
        this.dishName = dishName;
        this.amount = amount;
        this.dishPrice = dishPrice;
    }

    public OrderDetailsBean() {
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
}
