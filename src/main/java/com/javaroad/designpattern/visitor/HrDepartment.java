package com.javaroad.designpattern.visitor;

/**
 *  具体访问者对象：公司人力资源部<br/>
 *  人力资源部的职责就是负责统计核算员工的每月上班时长
 */
public class HrDepartment extends Department{
    @Override
    public void visit(ManagerEmployee me) {
        System.out.println("管理者上班时长:"+me.getTotalTimeSheet());
    }

    @Override
    public void visit(GeneralEmployee ge) {
        System.out.println("普通员工上班时长:"+ge.getTotalTimeSheet());
    }
}
