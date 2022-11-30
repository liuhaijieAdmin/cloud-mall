package com.zhuzi.mallbase.service.service;

import com.zhuzi.mallbase.model.entity.Inventory;
import com.zhuzi.mallbase.service.fallbackFactory.OrderFeginSrviceFallbackFactoy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "MALL-INVENTORY", fallbackFactory = OrderFeginSrviceFallbackFactoy.class)
public interface InventoryFeginSrvice {

    @RequestMapping(value = "/inventory/get/{id}",method = RequestMethod.GET)
    Inventory get(@PathVariable("id") String inventoryId);

    @RequestMapping(value = "/inventory/add",method = RequestMethod.GET)
    int add(Inventory inventory);

    @RequestMapping(value = "/inventory/edit",method = RequestMethod.GET)
    int edit(Inventory inventory);

}
