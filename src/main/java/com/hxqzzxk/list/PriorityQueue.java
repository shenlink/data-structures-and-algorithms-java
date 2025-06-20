package com.hxqzzxk.list;

import java.util.Comparator;

/**
 * 优先队列实现
 * 底层使用二叉堆实现，默认为最小堆
 *
 * @param <E> 元素类型
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<E> {
    /**
     * 存储元素的数组
     */
    private E[] elements;
    /**
     * 队列中实际元素个数
     */
    private int size;
    /**
     * 元素比较器，默认为null，此时使用元素的自然顺序进行比较
     */
    private Comparator<E> comparator;
    /**
     * 堆底层的数组的默认初始容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 创建一个优先队列
     *
     * @param comparator 用于比较元素优先级的比较器
     */
    public PriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * 创建默认优先队列
     * 使用默认比较器（元素自然顺序）
     */
    public PriorityQueue() {
        this(null);
    }

    /**
     * 入队操作
     *
     * @param element 要入队的元素
     */
    public void enQueue(E element) {
        // 元素不能为null
        checkElement(element);
        // 扩容判断：当插入新元素后超出当前容量时进行扩容
        expansion(size + 1);
        // 将元素添加到数组末尾（堆底）
        elements[size] = element;
        size++;
        // 新元素上滤操作：从最后一个位置开始向上调整堆，恢复堆特性
        siftUp(size - 1);
    }

    /**
     * 出队操作
     *
     * @return 优先级最高的元素
     */
    public E deQueue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        // 取出堆顶元素作为返回值
        E root = elements[0];
        // 将最后一个元素移到堆顶位置
        elements[0] = elements[--size];
        elements[size] = null; // 清空最后一个位置的引用
        // 如果堆中还有元素，则从堆顶开始下滤操作
        if (size > 0) {
            siftDown(0);
        }
        return root;
    }

    /**
     * 获取队首元素（优先级最高的元素）
     *
     * @return 队首元素
     */
    public E front() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return elements[0];
    }

    /**
     * 清空队列
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 获取队列中的元素数量
     *
     * @return 元素数量
     */
    public int size() {
        return size;
    }

    /**
     * 检查队列是否为空
     *
     * @return 如果队列为空则返回 true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 上滤操作：将指定索引位置的元素向上调整到合适的位置，以维持堆特性。
     *
     * @param index 需要上滤的元素索引
     */
    private void siftUp(int index) {
        // 备份新添加的元素
        E element = elements[index];
        // index > 0的时候才有上滤的必要
        while (index > 0) {
            // 计算父节点索引
            int parentIndex = (index - 1) >> 1;
            // 获取父节点
            E parent = elements[parentIndex];
            // 如果要添加的元素小于等于这个父节点，则说明上滤到了合适的位置
            if (compare(element, parent) <= 0) {
                break;
            }
            // 父节点更大，需要将父节点下移至当前位置
            elements[index] = parent;
            // 继续向上检查，更新索引为父节点位置
            index = parentIndex;
        }
        // 在合适位置放置原始元素
        elements[index] = element;
    }

    /**
     * 下滤操作：将指定索引位置的元素向下调整到合适的位置，以维持堆特性。
     *
     * @param index 需要下滤的元素索引
     */
    private void siftDown(int index) {
        // 备份当前元素
        E element = elements[index];
        // half是最后一个非叶子节点的索引
        int half = size >> 1;
        while (index < half) {
            // 左子节点索引
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            // 右子节点索引
            int rightIndex = childIndex + 1;
            // 判断是否存在右子节点，并选择较大的子节点
            if (rightIndex < size) {
                E right = elements[rightIndex];
                if (compare(right, child) > 0) {
                    child = right;
                    childIndex = rightIndex;
                }
            }
            // 如果当前元素大于等于较大子节点，则满足堆特性
            if (compare(element, child) >= 0) {
                break;
            }
            // 较大的子节点上移至当前节点位置
            elements[index] = child;
            // 继续向下检查，更新索引为较大子节点位置
            index = childIndex;
        }
        // 在合适位置放置原始元素
        elements[index] = element;
    }

    /**
     * 比较两个元素的大小
     *
     * @param e1 第一个元素
     * @param e2 第二个元素
     * @return 比较结果
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 验证元素，元素不能为null
     *
     * @param element 待验证的元素
     */
    private void checkElement(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 扩容，数组容量不够，扩容加大容量到原始容量的1.5倍
     *
     * @param capacity 需要达到的最小容量
     */
    private void expansion(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        // 按照1.5倍比例扩容
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 创建新的数组容器
        E[] newElements = (E[]) new Object[newCapacity];
        // 将原有数据迁移至新数组
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        // 更新底层存储数组
        elements = newElements;
    }
}