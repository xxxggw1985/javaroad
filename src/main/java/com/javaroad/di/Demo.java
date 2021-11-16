package com.javaroad.di;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-05 20:17
 * @version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        RateLimiter rateLimiter = (RateLimiter)applicationContext.getBean("rateLimiter");
        rateLimiter.test();
    }
}
