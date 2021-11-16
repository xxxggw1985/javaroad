package com.javaroad.designpattern.decorator;

import java.io.*;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-02 22:13
 * @version: 1.0
 */
public class Starter {
    public static void main(String[] args) throws IOException {
        /**
         * FileInputStream的父类是InputStream（抽象类），则FileInputStream是必须要实现所有InputStream的方法
         * BufferedInputStream的父类是FilterInputStream，FilterInputStream的所有方法都委托给InputStream类了。所以BufferedInputStream只需要重写有限的方法就可以了
         * 装饰模式的特点：1可以进行功能的增强。代理模式是增加不同的功能 2 可以对原始类多次装饰
         * 如果不用装饰模式来解决，用基础来实现功能的累加，可能就有类爆炸的情况出现了
         */
        InputStream in = new FileInputStream("D:\\workspace\\java-road\\src\\main\\java\\com.javaroad.designpattern\\decorator\\test.txt");
        InputStream bin = new BufferedInputStream(in);
        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
        }
    }

}
