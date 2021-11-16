package com.javaroad.object;

public class Starter {
    public static void main(String[] args) {
        /**
         * Java中父类强制转换成子类的原则：父类型的引用指向的是哪个子类的实例，就能转换成哪个子类的引用。
         */
        Parent parent = new Child();
        parent.setParentId(1);
        Child child = (Child)parent;
        System.out.println(child);

        /**
         * 反面例子
         */
        Parent parent1 = new Parent();
        parent.setParentId(1);
        Child child1 = (Child)parent1;//ClassCastException
        System.out.println(child1);
    }
}
