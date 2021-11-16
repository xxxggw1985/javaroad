package com.javaroad.singleList;

/**
 * 基于单链表LRU算法
 */
public class LRUBaseLinkedList<T> {
    /**
     * 默认单链表容量
     */
    private static  final Integer DEFAULT_CAPACITY = 10;
    /**
     * 头节点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList(){
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0 ;
    }
    public LRUBaseLinkedList(Integer capacity){
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0 ;
    }

//    public void add(T data){
//        SNode preNode = findPreNode(data);
//        //链表中存在，删除原数据，再插入到链表的顶部
//        if(preNode != null){
//            deleteElemOptim(preNode);
//            insertElemAtBegin(data);
//        } else {
//            if(length >= this.capacity){
//                deleletElemAtEnd();
//            }
//            insertElemAtBegin(data);
//        }
//    }
//
//    /**
//     * 删除preNode的下一个节点
//     * @param preNode
//     */
//    private void deleteElemOptim(SNode preNode) {
//        SNode temp = preNode.getNext();
//        preNode.setNext
//    }
//
    public class SNode<T> {

    }
}

