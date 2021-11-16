package com.javaroad.di;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-05 21:06
 * @version: 1.0
 */
public enum Scope {
    SINGLETON,PROTOTYPE;
    public static Scope fromConst(String scope){
        if(scope.toUpperCase().equals("SINGLETON")){
            return  SINGLETON;
        }else {
            return PROTOTYPE;
        }
    }
}
