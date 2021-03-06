package com.javaroad.designpattern.visitor;
/**
 **访问者模式
 * 访问者模式把数据结构和作用于结构上的操作解耦合，
 * 使得操作集合可相对自由地演化。
 * 访问者模式适用于数据结构相对稳定算法又易变化的系统。因为访问者模式使得算法操作增加变得容易。
 * 若系统数据结构对象易于变化，经常有新的数据对象增加进来，则不适合使用访问者模式。
 *
 * 1 优点
 * 扩展性好： 在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。
 * 复用性好： 通过访问者来定义整个对象结构通用的功能，从而提高复用程度。
 * 分离无关行为： 通过访问者来分离无关的行为，把相关的行为封装在一起，构成一个访问者，这样每一个访问者的功能都比较单一。
 * 2 缺点
 * 对象结构变化很困难： 不适用于对象结构中的类经常变化的情况，因为对象结构发生了改变，访问者的接口和访问者的实现都要发生相应的改变，代价太高。
 * 破坏封装： 访问者模式通常需要对象结构开放内部数据给访问者和ObjectStructrue，这破坏了对象的封装性。
 * 3 使用场景
 * 数据结构稳定，作用于数据结构的操作经常变化的时候。
 * 当一个数据结构中，一些元素类需要负责与其不相关的操作的时候，为了将这些操作分离出去，以减少这些元素类的职责时，可以使用访问者模式。
 * 有时在对数据结构上的元素进行操作的时候，需要区分具体的类型，这时使用访问者模式可以针对不同的类型，在访问者类中定义不同的操作，从而去除掉类型判断
 *
 * 4 源码关键点：
 */
