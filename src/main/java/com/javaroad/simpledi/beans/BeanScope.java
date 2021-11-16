package com.javaroad.simpledi.beans;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 14:37
 * @version: 1.0
 */
public enum BeanScope {
    DEFAULT("singleton"),
    SINGLETON("singleton"),
    PROTOTYPE("prototype");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    BeanScope(String name){
        this.name = name;
    }

    public static BeanScope of(String name){
        for(BeanScope beanScope  : values()){
            if(beanScope.name.equals(name) ){
                return beanScope;
            }
        }
        throw  new IllegalArgumentException("bean scope ["+name+"] not defined" );
    }
    public boolean isSingleton(){
        return "singleton".equals(this.name);
    }
    public boolean isPrototype(){
        return "prototype".equals(this.name);
    }

}
