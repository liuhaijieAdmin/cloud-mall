package com.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注： 自定义算法不能与主启动类在同一包或该包子包下，不然自定义 算法将在所有服务中共享
 * */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule_LHJ();// 使用自定义算法
    }

}
