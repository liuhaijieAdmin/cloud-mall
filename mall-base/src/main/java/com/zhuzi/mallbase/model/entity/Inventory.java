package com.zhuzi.mallbase.model.entity;

import java.io.Serializable;

public class Inventory implements Serializable {
    private String inventoryId;

    private String shopName;

    private Integer shopCount;

    public Inventory() {
    }

    public Inventory(String inventoryId, String shopName, Integer shopCount) {
        this.inventoryId = inventoryId;
        this.shopName = shopName;
        this.shopCount = shopCount;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getShopCount() {
        return shopCount;
    }

    public void setShopCount(Integer shopCount) {
        this.shopCount = shopCount;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId='" + inventoryId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopCount=" + shopCount +
                '}';
    }
}