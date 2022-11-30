package com.zhuzi.mallserverorder;

import com.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient //本服务启动会自动注册进Eureka服务中心
@EnableDiscoveryClient //服务发现
@RibbonClient(name = "MALL-SERVER-INVENTORY",configuration = MySelfRule.class)
@ComponentScan(basePackages = {"com.zhuzi"})
public class MallServerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallServerOrderApplication.class, args);
    }
}
