package com.zhuzi.mallserverinventory.service.impl;

import com.zhuzi.distributedtx.annotation.ZhuziTransactional;
import com.zhuzi.mallbase.model.entity.Inventory;
import com.zhuzi.mallserverinventory.mapper.InventoryMapper;
import com.zhuzi.mallserverinventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    @Transactional
    public Integer deleteByPrimaryKey(String inventoryId) {
        return inventoryMapper.deleteByPrimaryKey(inventoryId);
    }

    @Override
    @Transactional
    public Integer insert(Inventory record) {
        return inventoryMapper.insert(record);
    }

    @Override
    @Transactional
    public Integer insertSelective(Inventory record) {
        return inventoryMapper.insertSelective(record);
    }

    @Override
    public Inventory selectByPrimaryKey(String inventoryId) {
        return inventoryMapper.selectByPrimaryKey(inventoryId);
    }

    @Override
    @Transactional
    @ZhuziTransactional(isStart = true)
    public Integer updateByPrimaryKeySelective(Inventory record) {
        int i = inventoryMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    @Transactional
    public Integer updateByPrimaryKey(Inventory record) {
        return inventoryMapper.updateByPrimaryKey(record);
    }
}
