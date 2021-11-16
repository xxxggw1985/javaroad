package com.javaroad.di;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-02 22:59
 * @version: 1.0
 */
public class RedisCounter {
    private String ipAddress;
    private int port;
    public RedisCounter(String ipAddress,int port){
        this.ipAddress = ipAddress;
        this.port = port;
    }
}
