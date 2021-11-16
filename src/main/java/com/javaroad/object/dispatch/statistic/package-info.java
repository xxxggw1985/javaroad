package com.javaroad.object.dispatch.statistic;
/**
 ** 量被声明时的类型叫做变量的静态类型(Static Type)，
 * 有些人又把静态类型叫做明显类型(Apparent Type)；而变量所引用的对象的真实类型又叫做变量的实际类型(Actual Type)。
 *
 * 比如：
 *
 * Map map = null;
 *
 * map = new HashMap();
 * 　 声明了一个变量map，它的静态类型（也叫明显类型）是Map，而它的实际类型是HashMap。
 *
 * 　　根据对象的类型而对方法进行的选择，就是分派(Dispatch)，分派(Dispatch)又分为两种，即静态分派和动态分派。
 *
 * 　　静态分派(Static Dispatch) 发生在编译时期，分派根据静态类型信息发生。静态分派对于我们来说并不陌生，方法重载就是静态分派。
 *
 * 　　动态分派(Dynamic Dispatch) 发生在运行时期，动态分派动态地置换掉某个方法。
 *
 * 　　静态分派：Java通过方法重载支持静态分派。
 *
 * 　　动态分派：Java通过方法的重写支持动态分派
 */