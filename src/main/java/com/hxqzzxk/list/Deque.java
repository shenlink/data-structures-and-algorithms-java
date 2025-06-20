package com.hxqzzxk.list;

/**
 * 双端队列实现类，使用双向链表作为底层实现
 * @param <E> 队列中元素的类型
 */
public class Deque<E> {
    /**
     * 使用双向链表实现
     */
    private final List<E> list = new DoubleLinkedList<>();

    /**
     * 将元素添加到队尾
     * 
     * @param element 要添加的元素
     */
    public void enQueueRear(E element) {
        list.add(element);
    }

    /**
     * 移除并返回队头元素
     * 
     * @return 队头元素
     */
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     * 将元素添加到队头
     * 
     * @param element 要添加的元素
     */
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    /**
     * 移除并返回队尾元素
     * 
     * @return 队尾元素
     */
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    /**
     * 获取但不移除队头元素
     * 
     * @return 队头元素
     */
    public E front() {
        return list.get(0);
    }

    /**
     * 获取但不移除队尾元素
     * 
     * @return 队尾元素
     */
    public E rear() {
        return list.get(list.size() - 1);
    }

    /**
     * 清空队列中的所有元素
     */
    public void clear() {
        list.clear();
    }

    /**
     * 返回队列中的元素数量
     * 
     * @return 队列中的元素数量
     */
    public int size() {
        return list.size();
    }

    /**
     * 判断队列是否为空
     * 
     * @return 如果队列为空则返回true
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}