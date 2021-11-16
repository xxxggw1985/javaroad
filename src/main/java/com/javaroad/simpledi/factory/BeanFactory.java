package com.javaroad.simpledi.factory;


import java.util.List;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 14:17
 * @version: 1.0
 */
public interface BeanFactory {
    Object getBean(String beanId);
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;
    List<Object> getBeansByType(Class<?> type);
}
