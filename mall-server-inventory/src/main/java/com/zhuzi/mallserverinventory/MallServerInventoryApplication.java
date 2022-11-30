package com.zhuzi.mallserverinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient //本服务启动会自动注册进Eureka服务中心
@EnableDiscoveryClient //服务发现
@ComponentScan(basePackages = {"com.zhuzi"})
public class MallServerInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallServerInventoryApplication.class, args);
    }

}
