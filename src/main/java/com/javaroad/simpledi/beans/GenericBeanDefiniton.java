package com.javaroad.simpledi.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 16:38
 * @version: 1.0
 */
public class GenericBeanDefiniton implements BeanDefinition {
    private String beanId;
    private String beanClassName;
    private BeanScope beanScope = BeanScope.DEFAULT;
    private final List<PropertyValue> propertyValues = new ArrayList<>();
    public GenericBeanDefiniton(String beanId, String beanClassName) {
        this.beanId = beanId;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getId() {
        return beanId;
    }

    @Override
    public void setId(String id) {
        this.beanId = id;
    }

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }

    @Override
    public boolean isSingleton() {
        return beanScope.isSingleton();
    }

    @Override
    public boolean isPrototype() {
        return beanScope.isPrototype();
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return propertyValues   ;
    }

    @Override
    public BeanScope getScope() {
        return beanScope;
    }

    @Override
    public void setScope(BeanScope beanScope) {
        this.beanScope = beanScope;
    }
}
