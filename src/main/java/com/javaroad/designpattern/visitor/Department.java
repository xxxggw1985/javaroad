package com.javaroad.designpattern.visitor;

public abstract class Department {
    //声明一组重载的方法，用于访问不同类型的具体元素（这里指不同的员工）


    /**
     * 抽象方法，访问管理者对象。
     * 具体调用什么方法，就有具体的访问者子类去实现（这里指的是不同的部门）
     * @param managerEmployee
     */
    public abstract void visit(ManagerEmployee managerEmployee) ;

    /**
     * 抽象方法，访问普通员工对象。
     * 具体调用什么方法，就有具体的访问者子类去实现（这里指的是不同的部门）
     * @param generalEmployee
     */
    public abstract void visit(GeneralEmployee generalEmployee)  ;
}
