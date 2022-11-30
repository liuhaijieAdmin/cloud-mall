package com.zhuzi.mallbase.service.service;

import com.zhuzi.mallbase.model.entity.Order;
import com.zhuzi.mallbase.service.fallbackFactory.OrderFeginSrviceFallbackFactoy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "MALL-ORDER", fallbackFactory = OrderFeginSrviceFallbackFactoy.class)
@Service
public interface OrderFeginSrvice {

    @RequestMapping(value = "/order/get/{id}",method = RequestMethod.GET)
    Order get(@PathVariable("id") String orderId);

    @RequestMapping(value = "/order/add",method = RequestMethod.GET)
    int add(Order order);

    @RequestMapping(value = "/placeAnOrder",method = RequestMethod.GET)
    String placeAnOrder(Order order);

}

