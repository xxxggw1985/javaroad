package com.javaroad.di;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-05 20:47
 * @version: 1.0
 */
public class NoSuchBeanDefinitionException extends RuntimeException {
    public NoSuchBeanDefinitionException(String s) {
        super(s);
    }
}
