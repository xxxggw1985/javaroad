package com.javaroad.object.initialization;

public class FinalDemo {

    public static final String A;
    static {
        A= "静态初始化块初始化";
        System.out.println(A);
    }
    public static final String A1 = "static final直接赋值";
    public static final FinalDemo c = new FinalDemo("3");
    private  final String B;
    private  final String B1 = "final直接赋值";



    {
        B= "初始化块初始化";
        System.out.println(B);
    }
    public String getB(){
        return B;
    }
    public FinalDemo(String b) {
//        B = b;
    }

    public static void main(String[] args) {
        FinalDemo demo1 = new FinalDemo("构造方法初始化demo1");
        System.out.println(demo1.B);
        System.out.println(demo1.B);
//        FinalDemo demo2 = new FinalDemo("构造方法初始化demo2");
//        System.out.println(demo2.B);
//
//        System.out.println(FinalDemo.A);
//        System.out.println(FinalDemo.A1);
//        System.out.println(FinalDemo.c.B);
//        System.out.println(FinalDemo.c.B1);
    }
}
