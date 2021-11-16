package com.javaroad.designpattern.visitor;

/**
 * 公司员工（被访问者）抽象类
 */
public abstract class Employee {
    public abstract void accept(Department department);
}
