package com.zhuzi.mallserverinventory.thread;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

// 分布式锁的守护线程
public class GuardThread extends Thread {

    private String lockKey;
    private int timeOut;
    private StringRedisTemplate stringRedisTemplate;

    public GuardThread(String lockKey, int timeOut, StringRedisTemplate stringRedisTemplate){
        this.lockKey = lockKey;
        this.timeOut = timeOut;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(timeOut / 2 * 1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            Long expire = stringRedisTemplate.getExpire(lockKey, TimeUnit.SECONDS);
            if (expire > 0){
                stringRedisTemplate.expire(lockKey,expire + timeOut/2,TimeUnit.SECONDS);
            }
        }
    }
}
