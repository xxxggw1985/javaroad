package com.javaroad.di;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-05 20:20
 * @version: 1.0
 */

public class ClassPathXmlApplicationContext implements ApplicationContext {
    private BeansFactory  beansFactory;
    private BeanConfigParser beanConfigParser;
    public ClassPathXmlApplicationContext(String configLocation){
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinition(configLocation);
    }

    private void loadBeanDefinition(String configLocation) {
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream("/"+configLocation);
            if(in == null){
                throw new RuntimeException("can not found the com.javaroad.transaction.config file:"+configLocation);
            }
            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(in);
            beansFactory.addBeanDefinitions(beanDefinitions);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (IOException ex){
                    //log error
                    System.out.println(ex);
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        return beansFactory.getBean(beanId);
    }
}
