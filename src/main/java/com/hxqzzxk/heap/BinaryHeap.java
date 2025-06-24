package com.hxqzzxk.heap;

import java.util.Comparator;

/**
 * 二叉堆
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> {
    /**
     * 堆底层的数组
     */
    private E[] elements;

    /**
     * 堆底层的数组的默认容量
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * 使用指定元素数组和比较器构造一个二叉堆。
     *
     * @param elements   要使用的元素数组
     * @param comparator 用于比较元素的比较器
     */
    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        // 重载父类构造函数，初始化比较器
        super(comparator);

        // 如果底层数组为空，则使用默认容量初始化数组
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            // 使用外部的elements数组初始化堆的底层数组数组
            size = elements.length;
            int capacity = Math.max(DEFAULT_CAPACITY, size);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                this.elements[i] = elements[i];
            }
            // 批量建堆
            heapify();
        }
    }

    /**
     * 使用指定元素数组构造一个二叉堆，默认不提供比较器。
     *
     * @param elements 要使用的元素数组
     */
    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    /**
     * 使用指定比较器构造一个空的二叉堆。
     *
     * @param comparator 用于比较元素的比较器
     */
    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 使用默认配置构造一个空的二叉堆。
     */
    public BinaryHeap() {
        this(null, null);
    }

    /**
     * 清除堆中的所有元素。
     */
    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 向堆中添加一个元素。
     *
     * @param element 要添加的元素
     */
    @Override
    public void add(E element) {
        // 元素不能为null
        checkElement(element);
        // 扩容
        expansion(size + 1);
        // 添加到堆的底层数组有效元素的后一位
        elements[size] = element;
        size++;
        // 上滤
        siftUp(size - 1);
    }

    /**
     * 获取堆顶元素，但不移除它。
     *
     * @return 堆顶元素
     * @throws IndexOutOfBoundsException 如果堆为空
     */
    @Override
    public E get() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return elements[0];
    }

    /**
     * 移除并返回堆顶元素。
     * 用堆底元素覆盖堆顶元素，删除堆底元素，然后对堆顶元素进行下滤。
     *
     * @return 被移除的堆顶元素
     * @throws IndexOutOfBoundsException 如果堆为空
     */
    @Override
    public E remove() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        int lastIndex = size - 1;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        size--;
        // 对堆顶元素下滤
        siftDown(0);
        return root;
    }

    /**
     * 替换堆顶元素，并返回旧的堆顶元素。
     * 如果此时堆内没有元素，则添加该元素，并返回null。
     *
     * @param element 新的堆顶元素
     * @return 原来的堆顶元素，如果堆为空则返回 null
     */
    @Override
    public E replace(E element) {
        checkElement(element);
        if (size == 0) {
            elements[0] = element;
            size++;
            return null;
        }
        E oldElement = elements[0];
        elements[0] = element;
        siftDown(0);
        return oldElement;
    }

    /**
     * 批量建堆操作，使用自下而上的下滤方式。
     */
    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 检查元素是否为 null。
     *
     * @param element 要检查的元素
     * @throws IllegalArgumentException 如果元素为 null
     */
    private void checkElement(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 根据需要扩展堆的容量。
     * 扩容加大容量到原始容量的1.5倍
     *
     * @param capacity 需要的最小容量
     */
    private void expansion(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * 对索引处的元素执行上滤操作以维护堆性质。
     *
     * @param index 需要上滤的元素索引
     */
    private void siftUp(int index) {
        // 备份新添加的元素
        E element = elements[index];
        // index > 0的时候才有上滤的必要，index == 1或index == 2的
        // 时候，就可能会上滤到堆顶，所以，这里是index > 0
        while (index > 0) {
            // 获得父节点的索引
            int parentIndex = (index - 1) >> 1;
            // 获取父节点
            E parent = elements[parentIndex];
            // 如果要添加的元素小于等于这个父节点，则说明上滤到了合适的位置，
            // 停止上滤
            if (compare(element, parent) <= 0) {
                break;
            }
            // 来到这里，说明父节点小于要添加的元素，
            // 则使用父节点元素覆盖index，也就是要添加的元素的索引
            elements[index] = parent;
            // 更新index，需要更新成父节点的索引，继续上滤
            index = parentIndex;
        }
        // 来到这里，说明上滤到了合适的位置，将备份的元素放在合适的位置
        // 注意：可能是break跳出循环或者是index == 0
        elements[index] = element;
    }

    /**
     * 对索引处的元素执行下滤操作以维护堆性质。
     *
     * @param index 需要下滤的元素索引
     */
    private void siftDown(int index) {
        // 备份元素
        E element = elements[index];
        // 有效索引的一半，因为只有当index < half的时候，
        int half = size >> 1;
        // half - 1是最后一个非叶子节点的索引，
        // 只有非叶子节点才有获取子节点的索引的操作
        while (index < half) {
            // 获取左子节点的索引
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            // 右子节点的索引
            int rightIndex = childIndex + 1;
            // 注意：右子节点的索引可能越界，所以，这里需要判断
            // 这里比较左子节点和右子节点的值，获取到更大的那个子节点
            if (rightIndex < size) {
                E right = elements[rightIndex];
                if (compare(right, child) > 0) {
                    child = right;
                    childIndex = rightIndex;
                }
            }
            // 比较元素，堆顶元素大于等于子节点，则说明下滤到了合适的位置
            if (compare(element, child) >= 0) {
                break;
            }
            // 子节点覆盖父节点的位置
            elements[index] = child;
            // 更新index，需要更新成子节点的索引值，继续下滤
            index = childIndex;
        }
        // 来到这里，说明下滤到了合适的位置，将备份的元素放在合适的位置
        elements[index] = element;
    }
}
