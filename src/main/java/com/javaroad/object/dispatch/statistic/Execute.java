package com.javaroad.object.dispatch.statistic;

public class Execute {
    public void execute(Dog dog){
        System.out.println("this is a dog");
    }
    public void execute(DogBaby1 dog){
        System.out.println("this is a DogBaby1");
    }
    public void execute(DogBaby2 dog){
        System.out.println("this is a DogBaby2");
    }

    public static void main(String[] args) {
        /**
         * 三次对execute()方法的调用传入的是不同的参数，分别是dog、baby1、baby2。它们虽然具有不同的真实类型，但是它们的静态类型都是一样的，均是Dog类型。
         *
         * 重载方法的分派是根据静态类型进行的，这个分派过程在编译时期就完成了。
         */
        Execute execute  = new Execute();
        Dog dog = new Dog();
        Dog dogBaby1 = new DogBaby1();
        Dog dogBaby2 = new DogBaby2();
        execute.execute(dog);
        execute.execute(dogBaby1);
        execute.execute(dogBaby2);

    }
}
