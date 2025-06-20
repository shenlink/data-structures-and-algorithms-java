package com.hxqzzxk.list;

/**
 * 循环双端队列实现
 * 使用循环数组实现高效的双端队列操作
 * 
 * @param <E> 元素类型
 */
public class CircleDeque<E> extends AbstractCircleQueue<E> {
    /**
     * 默认构造函数，使用默认容量创建循环双端队列
     */
    public CircleDeque() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 带容量参数的构造函数
     * 
     * @param capacity 初始容量
     */
    @SuppressWarnings("unchecked")
    public CircleDeque(int capacity) {
        // 指定的容量小于等于默认容量时，使用默认容量
        capacity = Math.max(DEFAULT_CAPACITY, capacity);
        elements = (E[]) new Object[capacity];
    }

    /**
     * 从队列尾部入队
     * 
     * @param element 要入队的元素
     */
    public void enQueueRear(E element) {
        expansion(size + 1);
        // 索引必须使用index(index)的方式，因为此时队列的front头部的真实索引可能不是0了
        elements[index(size)] = element;
        size++;
    }

    /**
     * 从队列的头部出队
     * 
     * @return 队头元素
     */
    public E deQueueFront() {
        E oldElement = elements[front];
        elements[front] = null;
        // 队头的索引更新到原先的队头的索引的下一个位置，原先的队头的索引时index(0)，
        // 所以此时的新的队头的索引是index(0 + 1)，也就是index(1)
        front = index(1);
        size--;
        // 缩容
        shrinking();
        return oldElement;
    }

    /**
     * 从队列头部入队
     * 
     * @param element 要入队的元素
     */
    public void enQueueFront(E element) {
        expansion(size + 1);
        // 此时的队头的索引是index(0)，所以队头的前一个索引就是index(-1)
        int newFront = index(-1);
        elements[newFront] = element;
        // 更新队头的索引
        front = newFront;
        size++;
    }

    /**
     * 从队列尾部出队
     * 
     * @return 队尾元素
     */
    public E deQueueRear() {
        // 计算出真实的队尾的索引
        int rearIndex = index(size - 1);
        E oldElement = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        // 缩容
        shrinking();
        return oldElement;
    }

    /**
     * 获取队尾元素
     * 
     * @return 队尾元素
     */
    public E rear() {
        return elements[index(size - 1)];
    }

    /**
     * 计算循环数组中的真实索引值
     * 
     * @param index 相对索引
     * @return 循环数组中的实际索引
     */
    protected int index(int index) {
        index += front;
        if (index < 0) {
            return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }
}