package com.javaroad.simpledi.beans;

import java.util.List;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 14:23
 * @version: 1.0
 */
public interface BeanDefinition {
    String getId();
    void  setId(String id);
    String getBeanClassName();
    boolean isSingleton();
    boolean isPrototype();
    List<PropertyValue> getPropertyValues();
    BeanScope getScope();
    void setScope(BeanScope beanScope);
}
