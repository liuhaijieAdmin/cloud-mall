package com.zhuzi.mallserverorder.service;


import com.zhuzi.mallbase.model.entity.Order;

public interface OrderService {

    Integer deleteByPrimaryKey(String orderId);

    Integer insert(Order record);

    Integer insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    Integer updateByPrimaryKeySelective(Order record);

    Integer updateByPrimaryKey(Order record);

}
