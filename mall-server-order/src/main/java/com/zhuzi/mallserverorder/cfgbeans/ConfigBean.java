package com.zhuzi.mallserverorder.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean { //applicationConfig.xml --> @Configuration配置

    @Bean
    //ribbon默认轮询算法
    @LoadBalanced //开启负载均衡   Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    //使用Ribbon其他算法
    @Bean
    public IRule myRule(){
        return new RandomRule(); //使用自己选择的随机算法代替Ribbon默认的轮询算法
    }

}
//<bean id="UserServicre" class="com.zking.service.UserServicre">
