package com.javaroad.simpledi.beans.support;

import com.javaroad.simpledi.beans.BeanDefinition;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 14:46
 * @version: 1.0
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanId, BeanDefinition beanDefinition);
    BeanDefinition getBeanDefinition(String beanId);
}
