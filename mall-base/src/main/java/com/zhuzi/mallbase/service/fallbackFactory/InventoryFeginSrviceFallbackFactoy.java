package com.zhuzi.mallbase.service.fallbackFactory;

import com.zhuzi.mallbase.model.entity.Inventory;
import com.zhuzi.mallbase.service.service.InventoryFeginSrvice;
import feign.hystrix.FallbackFactory;

public class InventoryFeginSrviceFallbackFactoy implements FallbackFactory<InventoryFeginSrvice> {

    @Override
    public InventoryFeginSrvice create(Throwable throwable) {
        return new InventoryFeginSrvice() {
            @Override
            public Inventory get(String inventoryId) {
                return new Inventory("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭","no this database in MySQL",-1);
            }

            @Override
            public int add(Inventory inventory) {
                return -1;
            }

            @Override
            public int edit(Inventory inventory) {
                return -1;
            }
        };
    }
}
