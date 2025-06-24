package com.hxqzzxk.heap;

/**
 * 堆接口，定义了堆的基本操作
 *
 * @param <E> 元素类型
 */
public interface Heap<E> {
    /**
     * 清空堆
     */
    void clear();

    /**
     * 获取堆大小
     *
     * @return 堆中元素的数量
     */
    int size();

    /**
     * 堆是否为空
     *
     * @return 如果堆为空则返回 true
     */
    boolean isEmpty();

    /**
     * 添加元素到堆中
     *
     * @param element 要添加的元素
     */
    void add(E element);

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    E get();

    /**
     * 删除堆顶元素并返回该元素
     *
     * @return 被删除的堆顶元素
     */
    E remove();

    /**
     * 修改堆顶元素并返回该元素
     * 如果此时堆内没有元素，则添加该元素并返回 null
     *
     * @param element 新的堆顶元素
     * @return 原来的堆顶元素，或当堆为空时返回 null
     */
    E replace(E element);
}
