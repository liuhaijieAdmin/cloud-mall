package com.zhuzi.mallserverinventory.service;


import com.zhuzi.mallbase.model.entity.Inventory;

public interface InventoryService {

    Integer deleteByPrimaryKey(String inventoryId);

    Integer insert(Inventory record);

    Integer insertSelective(Inventory record);

    Inventory selectByPrimaryKey(String inventoryId);

    Integer updateByPrimaryKeySelective(Inventory record);

    Integer updateByPrimaryKey(Inventory record);
}
