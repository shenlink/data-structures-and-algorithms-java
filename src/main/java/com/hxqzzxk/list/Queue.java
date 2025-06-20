package com.hxqzzxk.list;

/**
 * 普通队列实现，使用双向链表作为底层存储结构
 * 
 * @param <E> 元素类型
 */
public class Queue<E> {
    /**
     * 使用双向链表实现队列
     */
    private List<E> list = new DoubleLinkedList<>();

    /**
     * 入队操作
     * 
     * @param element 要入队的元素
     */
    public void enQueue(E element) {
        list.add(element);
    }

    /**
     * 出队操作
     * 
     * @return 队头元素
     */
    public E deQueue() {
        return list.remove(0);
    }

    /**
     * 获取队头元素
     * 
     * @return 队头元素
     */
    public E front() {
        return list.get(0);
    }

    /**
     * 清空队列
     */
    public void clear() {
        list.clear();
    }

    /**
     * 获取队列中的元素数量
     * 
     * @return 元素数量
     */
    public int size() {
        return list.size();
    }

    /**
     * 检查队列是否为空
     * 
     * @return 如果队列为空则返回 true
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}