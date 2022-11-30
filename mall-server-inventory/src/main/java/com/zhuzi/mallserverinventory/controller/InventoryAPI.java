package com.zhuzi.mallserverinventory.controller;

import com.zhuzi.mallbase.model.entity.Inventory;
import com.zhuzi.mallserverinventory.service.InventoryService;
import com.zhuzi.mallserverinventory.thread.GuardThread;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/inventory")
public class InventoryAPI {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    private String port;

//    @Autowired
//    private RedissonClient redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private AtomicInteger count = new AtomicInteger(0); //原子操作类

    @RequestMapping("/minusInventory")
    public String minusInventory(Inventory inventory) {
        Inventory inventoryResult = inventoryService.selectByPrimaryKey(inventory.getInventoryId());
        if (inventoryResult.getShopCount() <= 0) {
            return "库存不足，请联系卖家....";
        }
        inventoryResult.setShopCount(inventoryResult.getShopCount() - 1);
        int n = inventoryService.updateByPrimaryKeySelective(inventoryResult);
        System.out.println("库存信息：" + inventoryResult.toString());

        if (n > 0) {
            return "端口：" + port + ",库存扣减成功！！！";
        }
        return "端口：" + port + ",库存扣减失败！！！";
    }

    @RequestMapping("/get")
    public Inventory get(String inventoryId) {
        return inventoryService.selectByPrimaryKey(inventoryId);
    }

    @RequestMapping("/reload")
    public int reload() {
        Inventory inventory = new Inventory();
        inventory.setInventoryId("82391173-9dbc-49b6-821b-746a11dbbe5e");
        inventory.setShopCount(100);
        int i = inventoryService.updateByPrimaryKeySelective(inventory);
        return i;
    }

    @RequestMapping("/add")
    public String add() {
        Inventory inventory = new Inventory(UUID.randomUUID().toString(), "黄金竹子", 100);
        int i = inventoryService.insertSelective(inventory);
        return "" + i;
    }

    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices(); // 得到eureka里面所有服务
        System.out.println("-----------" + list);

        List<ServiceInstance> srvList = client.getInstances("MALL-SERVER-INVENTORY");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }
        return this.client;
    }

    /*
    * //自定义分布式锁-抢占优惠名额
    @RequestMapping("/robQuota")
    public String robQuota() {
        String distributedKey = "discounts_lock";
        String redisClientID = UUID.randomUUID().toString();
        CourseDiscounts courseDiscounts = courseDiscountsService.selectByPrimaryKey(1);
        int timeOut = 10;

        Boolean lockResult = stringRedisTemplate.opsForValue().setIfAbsent(distributedKey, redisClientID, timeOut, TimeUnit.SECONDS);
        if (!lockResult) {
            return "当前活动参与人数过多，请稍后重试！";
        }
        GuardThread guardThread = new GuardThread(distributedKey, timeOut, stringRedisTemplate);
        guardThread.setDaemon(true);
        guardThread.start();
        try {
            Integer discountsCount = courseDiscounts.getDiscountsCount();
            if (discountsCount > 0) {
                Thread.sleep(12000);
                courseDiscounts.setDiscountsCount(courseDiscounts.getDiscountsCount() - 1);
                courseDiscountsService.updateByPrimaryKeySelective(courseDiscounts);
                System.out.println("[端口：" + port + "],扣减一个名额,剩余名额数量：" + courseDiscounts.getDiscountsCount());
            } else {
                System.out.println("[端口：" + port + "],扣减失败,优惠名额不足！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (stringRedisTemplate.opsForValue().get(distributedKey).equals(redisClientID)) {
                stringRedisTemplate.delete(distributedKey);
            }
        }
        return "[端口：" + port + "] - " + courseDiscounts.toString();
    }
    * */

    //    // 扣库存接口
//    @RequestMapping("/minusInventoryByLock")
//    public String minusInventoryByLock(Inventory inventory) {  // 任务跑到一半，锁被超时释放的场景
//        String key = "lock-" + inventory.getInventoryId();
//        String value  = UUID.randomUUID().toString();
//        int timeout = 10;
//        Boolean f = stringRedisTemplate.opsForValue().setIfAbsent(key, value,10,TimeUnit.SECONDS);
//        if (!f){
//            if (threadCount.get(Thread.currentThread().getId()) < 5){
//                return minusInventory(inventory);
//            } else {
//                return "当前抢购人数较多，请刷新重试.....";
//            }
//        }
//        int n;
//        GuardThread guardThread = new GuardThread(key,timeout,stringRedisTemplate);
//        guardThread.setDaemon(true);
//        guardThread.start();
//        try {
//            Inventory inventoryResult = inventoryService.selectByPrimaryKey(inventory.getInventoryId());
//            if (inventoryResult.getShopCount() <= 0) {
//                return "库存不足，请联系卖家....";
//            }
//            inventoryResult.setShopCount(inventoryResult.getShopCount() - 1);
//            n = inventoryService.updateByPrimaryKeySelective(inventoryResult);
//            System.out.println("库存信息：" + inventoryResult.toString());
//        } finally {
//            if (!value.equals(stringRedisTemplate.opsForValue().get(key))){
//                return "非法释放";
//            }
//            stringRedisTemplate.delete(key);
//        }
//        if (n > 0) {
//            return "端口：" + port + ",库存扣减成功！！！";
//        } else {
//            return "端口：" + port + ",库存扣减失败！！！";
//        }
//    }

}
