package com.hxqzzxk.list;

/**
 * 循环队列实现
 * 使用循环数组实现高效的队列操作
 *
 * @param <E> 元素类型
 */
public class CircleQueue<E> extends AbstractCircleQueue<E> {
    /**
     * 默认构造函数，使用默认容量创建循环队列
     */
    public CircleQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 带容量参数的构造函数
     *
     * @param capacity 初始容量
     */
    @SuppressWarnings("unchecked")
    public CircleQueue(int capacity) {
        // 指定的容量小于等于默认容量时，使用默认容量
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    /**
     * 入队操作
     *
     * @param element 要入队的元素
     */
    public void enQueue(E element) {
        expansion(size + 1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 出队操作
     *
     * @return 队头元素
     */
    public E deQueue() {
        E oldElement = elements[front];
        elements[front] = null;
        // 之前的队头的真实索引是index(0)，出队后，队头的索引变成index(1)
        front = index(1);
        size--;
        // 缩容
        shrinking();
        return oldElement;
    }

    /**
     * 计算循环数组中的真实索引值
     *
     * @param index 相对索引
     * @return 循环数组中的实际索引
     */
    protected int index(int index) {
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }
}