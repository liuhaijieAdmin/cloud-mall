package com.zhuzi.mallbase.model.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderId;

    private String orderName;

    private String orderPrice;

    private String inventoryId;

    public Order() {
    }

    public Order(String orderId, String orderName, String orderPrice, String inventoryId) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.inventoryId = inventoryId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                ", inventoryId='" + inventoryId + '\'' +
                '}';
    }
}