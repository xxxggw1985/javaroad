package com.javaroad.di;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-05 20:57
 * @version: 1.0
 */
public class BeanCreationFailureException extends RuntimeException {
    public BeanCreationFailureException(String s, Exception e) {
        super(s,e);
    }
}
