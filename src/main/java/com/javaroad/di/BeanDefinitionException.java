package com.javaroad.di;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-17 23:17
 * @version: 1.0
 */
public class BeanDefinitionException extends RuntimeException {
    public BeanDefinitionException(String s, Exception e) {
        super(s,e);
    }
}
