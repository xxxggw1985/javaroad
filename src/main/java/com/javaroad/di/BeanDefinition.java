package com.javaroad.di;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-05 20:40
 * @version: 1.0
 */
public class BeanDefinition {
    private String id;
    private Boolean lazyInit;

    private Scope scope = Scope.SINGLETON;
    private String className;
    private List<ConstructorArg> constructorArgs = new ArrayList<>();

    public String getId() {
        return  id;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }
    public String getClassName(){
        return className;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return this.constructorArgs;
    }




    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public static class ConstructorArg {
        private Boolean isRef;
        private Class type;
        private Object arg;
    }
}
