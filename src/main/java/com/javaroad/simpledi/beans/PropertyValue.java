package com.javaroad.simpledi.beans;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 14:32
 * @version: 1.0
 */
public class PropertyValue {
    private final String name;
    private final Object value;
    private boolean converted = true;
    private Object convertedValue;

    public PropertyValue(String name,Object value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object getConvertedValue() {
        return convertedValue;
    }
}
