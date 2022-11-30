package com.zhuzi.mallbase.service.fallbackFactory;

import com.zhuzi.mallbase.model.entity.Order;
import com.zhuzi.mallbase.service.service.OrderFeginSrvice;
import feign.hystrix.FallbackFactory;

public class OrderFeginSrviceFallbackFactoy implements FallbackFactory<OrderFeginSrvice> {

    @Override
    public OrderFeginSrvice create(Throwable throwable) {
        return new OrderFeginSrvice() {
            @Override
            public Order get(String orderId) {
                return new Order("id:"+orderId,"NULL","没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭","no this database in MySQL");
            }

            @Override
            public int add(Order order) {
                return -1;
            }

            @Override
            public String placeAnOrder(Order order) {
                return "当前请求人数较多，请稍后再试.......";
            }
        };
    }
}
