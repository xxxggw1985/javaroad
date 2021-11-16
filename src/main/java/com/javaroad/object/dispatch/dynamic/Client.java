package com.javaroad.object.dispatch.dynamic;

public class Client {
    public static void main(String[] args) {
        Dog dogbaby1 = new DogBaby1();
        dogbaby1.execute();
        Dog dogbaby2 = new DogBaby2();
        dogbaby2.execute();
    }
}
