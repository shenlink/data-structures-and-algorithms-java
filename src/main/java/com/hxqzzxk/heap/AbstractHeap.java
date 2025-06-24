package com.hxqzzxk.heap;

import java.util.Comparator;

/**
 * 抽象的堆实现，提供堆操作的基础功能
 */
@SuppressWarnings("unchecked")
public abstract class AbstractHeap<E> implements Heap<E> {
    /**
     * 堆里面元素的个数
     */
    protected int size;
    
    /**
     * 比较器
     */
    protected Comparator<E> comparator;

    /**
     * 使用指定比较器构造一个抽象堆
     *
     * @param comparator 用于比较堆中元素的比较器
     */
    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 构造一个没有比较器的抽象堆，默认使用自然排序
     */
    public AbstractHeap() {
        this(null);
    }

    /**
     * 获取堆中的元素数量
     *
     * @return 堆中元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 判断堆是否为空
     *
     * @return 如果堆为空则返回 true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 比较两个元素的顺序
     *
     * @param e1 第一个元素
     * @param e2 第二个元素
     * @return 比较结果：负值表示 e1 小于 e2，零表示相等，正值表示 e1 大于 e2
     */
    protected int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }
}
