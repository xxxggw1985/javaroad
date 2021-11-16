package com.javaroad.di;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-05 20:30
 * @version: 1.0
 */
public class BeansFactory {
    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<String, Object>();
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitions = new ConcurrentHashMap<String, BeanDefinition>();
    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList){
        for(BeanDefinition beanDefinition : beanDefinitionList){
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(),beanDefinition);
        }
        for(BeanDefinition beanDefinition : beanDefinitionList){
            if(beanDefinition.isLazyInit() == false  && beanDefinition.isSingleton()){
                createBean(beanDefinition);
            }
        }
    }
//    @VisibleForTesting
    protected Object createBean(BeanDefinition beanDefinition) {
        if(beanDefinition.isSingleton() && singletonObjects.contains(beanDefinition.getId())){
           return singletonObjects.get(beanDefinition.getId());
        }
        Object bean = null;
        try {
            Class beanClass = Class.forName(beanDefinition.getClassName());
            List<BeanDefinition.ConstructorArg> args = beanDefinition.getConstructorArgs();
            if(args.isEmpty()){
                bean = beanClass.newInstance();
            }
        } catch (ClassNotFoundException |InstantiationException| IllegalAccessException ex) {
           throw new BeanCreationFailureException("",ex);
        }
        if(bean !=null && beanDefinition.isSingleton()){
            singletonObjects.putIfAbsent(beanDefinition.getId(),bean);
            return singletonObjects.get(beanDefinition.getId());
        }
        return bean;

    }

    public Object getBean(String beanId) {
       BeanDefinition beanDefinition = beanDefinitions.get(beanId);
       if(beanDefinition == null){
           throw  new NoSuchBeanDefinitionException("bean is not defined:"+beanId);
       }
       return createBean(beanDefinition);

    }
}
