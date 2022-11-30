package com.zhuzi.mallhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//开启Dashboard仪表盘图形化监听
@EnableHystrixDashboard
public class MallHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallHystrixDashboardApplication.class, args);
    }

}
