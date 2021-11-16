package com.javaroad.di;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-02 22:59
 * @version: 1.0
 */
public class RateLimiter {
    private RedisCounter redisCounter;
    public RateLimiter(RedisCounter redisCounter){
        this.redisCounter = redisCounter;
    }
    public void test(){
        System.out.println("hello  world");
    }
}
