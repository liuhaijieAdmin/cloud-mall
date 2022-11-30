package com.zhuzi.malleureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//exclude = DataSourceAutoConfiguration.class --> 禁止springboot项目启动时调用数据源配置（无视数据源 -- 消费端调用提供端服务）
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer  //告诉spring该工程是Eureka注册服务中心,启动EurekaServer组件服务接收其他微服务注册进来
public class MallEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallEurekaApplication.class, args);
    }

}
