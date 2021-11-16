package com.javaroad.simpledi.factory;

import com.javaroad.simpledi.exception.BeansException;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 14:19
 * @version: 1.0
 */
public class NoSuchBeanDefinitionException extends BeansException {
    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }

    public NoSuchBeanDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
