package com.zhuzi.mallfegin.controller;

import com.zhuzi.mallbase.model.entity.Order;
import com.zhuzi.mallbase.service.service.OrderFeginSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/orderConsumer")
public class OrderServerConsumer {

    @Autowired
    private OrderFeginSrvice orderFeginSrvice;

    @RequestMapping("/placeAnOrder")
    public String placeAnOrder(Order order){
        return orderFeginSrvice.placeAnOrder(order);
    }

    @RequestMapping("/get")
    public Order get(String orderId){
        return orderFeginSrvice.get(orderId);
    }

    @RequestMapping("/add")
    public String add(){
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderName("黄金竹子");
        order.setOrderPrice("8888.88");
        order.setInventoryId(UUID.randomUUID().toString());
        int i = orderFeginSrvice.add(order);
        return "" + i;
    }

}
