package com.zhuzi.mallserverorder.service.impl;

import com.zhuzi.distributedtx.annotation.ZhuziTransactional;
import com.zhuzi.mallbase.model.entity.Order;
import com.zhuzi.mallserverorder.mapper.OrderMapper;
import com.zhuzi.mallserverorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Integer deleteByPrimaryKey(String orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public Integer insert(Order record) {
        return orderMapper.insert(record);

    }

    @Override
    @Transactional
    @ZhuziTransactional(isEnd = true)
    public Integer insertSelective(Order record) {
        // 刻意抛出一个异常
        int i = 100 / 0;
        int n = orderMapper.insertSelective(record);
        System.out.println("\n\n\n" + n + "\n\n\n");
        return n;
    }

    @Override
    public Order selectByPrimaryKey(String orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }
}
