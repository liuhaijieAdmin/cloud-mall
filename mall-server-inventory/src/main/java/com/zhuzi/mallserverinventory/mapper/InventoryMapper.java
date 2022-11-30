package com.zhuzi.mallserverinventory.mapper;

import com.zhuzi.mallbase.model.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InventoryMapper {
    int deleteByPrimaryKey(String inventoryId);

    int insert(Inventory record);

    int insertSelective(Inventory record);

    Inventory selectByPrimaryKey(String inventoryId);

    int updateByPrimaryKeySelective(Inventory record);

    int updateByPrimaryKey(Inventory record);
}
