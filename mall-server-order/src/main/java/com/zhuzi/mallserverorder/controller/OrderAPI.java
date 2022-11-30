package com.zhuzi.mallserverorder.controller;

import com.zhuzi.distributedtx.util.HttpClient;
import com.zhuzi.mallbase.model.entity.Order;
import com.zhuzi.mallserverorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient client;

    //通过服务提供者地址访问
    //private static final String REST_URL_PREFIX = "http://localhost:8002/provider/dept";
    //通过微服务名字访问
    private static final String REST_URL_PREFIX = "http://MALL-SERVER-INVENTORY/inventory";
    // 库存服务远程的调用地址
    private static final String URL_PREFIX = "http://localhost:8002/inventory";

    /**
     * 使用restTemplate访问restful接口非常的简单粗暴无脑。
     * (url, requestMap, ResponseBean.class)这三个参数分别代表
     * REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
     * */
    @Autowired
    private RestTemplate restTemplate;

    // 下单接口
    @RequestMapping("/placeAnOrder")
    public String placeAnOrder(){
        // 随便指定一个商品的ID
        String inventoryId = "92b1162a-eb7a-4d72-9645-dea3fe03c8e2";
        // 然后通过HttpClient调用库存服务的减库存接口
        String result = HttpClient.get(URL_PREFIX +
                "/minusInventory?inventoryId=" + inventoryId);
        System.out.println("\n调用减库存接口后的响应结果：" + result + "\n");

        // 调用减库存接口成功后，向订单库中插入一笔订单记录
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId,"黄金竹子","8888.88",inventoryId);
        Integer n = orderService.insertSelective(order);
        System.out.println("\n\n\n" + n + "\n\n\n");

        return "下单调用成功，需要处理事务.....";
    }

    @RequestMapping("/reload")
    public String reload(){
        int responseResult = restTemplate.getForObject(REST_URL_PREFIX + "/reload", int.class);
        System.out.println("调用后响应结果：" + responseResult);
        if (responseResult > 0){
            return "重置成功！";
        }else {
            return "重置失败！";
        }
    }

    @RequestMapping("/get")
    public Order get(String orderId){
        return orderService.selectByPrimaryKey(orderId);
    }

    @RequestMapping("/add")
    public String add(){
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderName("黄金竹子");
        order.setOrderPrice("8888.88");
        order.setInventoryId(UUID.randomUUID().toString());
        int i = orderService.insertSelective(order);
        return "" + i;
    }

    @RequestMapping(value = "discovery", method = RequestMethod.GET)
    public Object discovery(){
        List<String> list = client.getServices(); // 得到eureka里面所有服务
        System.out.println("-----------" + list);

        List<ServiceInstance> srvList = client.getInstances("MALL-SERVER-ORDER");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }
        return this.client;
    }

}
