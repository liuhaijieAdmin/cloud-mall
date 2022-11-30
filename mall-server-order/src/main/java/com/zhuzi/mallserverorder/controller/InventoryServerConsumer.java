package com.zhuzi.mallserverorder.controller;

import com.zhuzi.mallbase.model.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/inventoryServerConsumer")
public class InventoryServerConsumer {

    //通过服务提供者地址访问
    //private static final String REST_URL_PREFIX = "http://localhost:8002/provider/dept";
    //通过微服务名字访问
    private static final String REST_URL_PREFIX = "http://MALL-SERVER-INVENTORY/inventory";

    /**
     * 使用restTemplate访问restful接口非常的简单粗暴无脑。
     * (url, requestMap, ResponseBean.class)这三个参数分别代表
     * REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
     * */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/discovery", Object.class);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Inventory get(String inventoryId){
        return restTemplate.getForObject(REST_URL_PREFIX + "/get?inventoryId="+inventoryId, Inventory.class);
    }

    /*@RequestMapping(value = "add", method = RequestMethod.POST)
    public boolean add(Dept dept){
        //(url, requestMap, ResponseBean.class)这三个参数分别代表REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
        return restTemplate.postForObject(REST_URL_PREFIX + "/add", dept, Boolean.class);
    }*/
}
