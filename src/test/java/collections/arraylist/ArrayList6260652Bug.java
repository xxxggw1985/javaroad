package collections.arraylist;


import com.javaroad.arraylist.BaseClass;
import com.javaroad.arraylist.SubClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 看到jdk8存活下来的bug。写三个例子理解一下
 * bug描述如下：
 *   public ArrayList(Collection<? extends E> c) {
 *         elementData = c.toArray();
 *         if ((size = elementData.length) != 0) {
 *             // c.toArray might (incorrectly) not return Object[] (see 6260652)
 *             if (elementData.getClass() != Object[].class)
 *                 elementData = Arrays.copyOf(elementData, size, Object[].class);
 *         } else {
 *             // replace with empty array.
 *             this.elementData = EMPTY_ELEMENTDATA;
 *         }
 *     }
 */
public class ArrayList6260652Bug {
    /**
     * SubClass继承自BaseClass，由于SubClass数组的每一个元素都是SubClass对象，所以 BaseClass[] baseArray = subArray这个强转型是不会错的
     * 这其实就是java对象的向上转型， 但是由于数组中的元素类型都是SubClass类型的，所以baseArray[0]=new BaseClass()会报错ArrayStoreException
     * 说明假如我们有一个Object[]数组，并不代表着我们可以将Object对象存进去，这取决于数组中元素的实际类型
     */
//   @Test(expected = ArrayStoreException.class)
   public void test1(){
        SubClass[] subArray = {new SubClass(),new SubClass()};
        //class [Lcollections.arraylist.SubClass;
       System.out.println(subArray.getClass());
       BaseClass[] baseArray = subArray;
       //class [Lcollections.arraylist.SubClass;
       System.out.println(baseArray.getClass());
       //throws java.lang.ArrayStoreException: collections.arraylist.BaseClass
       baseArray[1] = new BaseClass();
   }

    /**
     *   List<String> list = Arrays.asList("abc");需要注意，可以知道返回的实际类型是java.util.Arrays$ArrayList,而不是ArrayList。
     *   我们调用Object[] objArray = list.toArray();返回的时候String[]数组，所以不能将Object对象，放入objArray数组中
     */
//    @Test(expected = ArrayStoreException.class)
    public void test2(){
       List<String> list = Arrays.asList("abc");
       //class java.util.Arrays$ArrayList
        System.out.println(list.getClass());
        Object[] objArray = list.toArray();
        //class [Ljava.lang.String;
        System.out.println(objArray.getClass());
        // throws java.lang.ArrayStoreException: java.lang.Object

        objArray[0] = new Object();
    }

    /**
     * ArrayList对象的toArray()返回的时候Object[]数组，可以放入任何类型的对象
     *通过test2和test3可以看出，如果我们有1个List<String> stringList对象，当我们调用Object[] objectArray = stringList.toArray();的时候，
     * objectArray 并不一定能够放置Object对象。这就是源码中的注释：
     * c.toArray might (incorrectly) not return Object[] (see 6260652)。
     * 为了考虑这种情况(实际就是Arrays的内部类ArrayList有特殊处理：调用了Clone方法)，所以源码中进行了if判断，来防止错误的数组对象导致异常。
     * Arrays.copyOf(elementData, size, Object[].class);
     * 这个方法就是用来创建1个Object[]数组，这样数组中就可以存放任意对象了。
     */
    @Test
    public void test3(){
        List<String> dataList = new ArrayList<>();
        dataList.add("1");
        dataList.add("2");
        dataList.add("3");
        dataList.add("4");
        dataList.add("5");
        dataList.add("6");
        dataList.add("7");
        dataList.add("8");
        dataList.add("9");
        dataList.add("10");
        dataList.add("11");
        //class java.util.ArrayList
        System.out.println(dataList.getClass());
        Object[] objArray = dataList.toArray();
        //class [Ljava.lang.Object;
        System.out.println(objArray.getClass());
        objArray[0] = "";
        objArray[0] = 1;
        objArray[0] = new Object();


    }
}
