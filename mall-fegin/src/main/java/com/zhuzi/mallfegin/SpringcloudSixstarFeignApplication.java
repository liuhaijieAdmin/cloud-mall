package com.zhuzi.mallfegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//exclude = DataSourceAutoConfiguration.class --> 禁止springboot项目启动时调用数据源配置（无视数据源 -- 消费端调用提供端服务）
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient //向eureka注册服务
//此服务启动时开启feign负载均衡
@EnableFeignClients(basePackages= {"com.sixstar"})
@ComponentScan("com.sixstar")
public class SpringcloudSixstarFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSixstarFeignApplication.class, args);
    }

}
