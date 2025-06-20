package com.hxqzzxk.list;

/**
 * 抽象的循环队列实现
 * 提供了循环队列的基本操作和通用功能
 * 
 * @param <E> 元素类型
 */
public abstract class AbstractCircleQueue<E> {
    /**
     * 队列头部的索引
     */
    protected int front;

    /**
     * 队列中元素的数量
     */
    protected int size;

    /**
     * 存储队列元素的数组
     */
    protected E[] elements;

    /**
     * 默认容量
     */
    protected static final int DEFAULT_CAPACITY = 10;

    /**
     * 清空队列所有元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    /**
     * 获取队列中的元素数量
     * 
     * @return 元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 检查队列是否为空
     * 
     * @return 如果队列没有元素则返回 true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取队头元素
     * 
     * @return 队头元素
     */
    public E front() {
        return elements[front];
    }

    /**
     * 计算元素在底层数组中的实际索引
     * 
     * @param index 要计算的相对索引值
     * @return 实际索引值
     */
    protected abstract int index(int index);

    /**
     * 扩容队列底层数组
     * 当元素数量大于底层数组长度时，扩容到原来的1.5倍
     * 
     * @param capacity 需要达到的最小容量
     */
    protected void expansion(int capacity) {
        // 验证容量，如果此时的容量还没有大于队列底层数组的长度，不需要进行扩容
        if (checkCapacity(capacity)) {
            return;
        }
        int oldCapacity = elements.length;
        // 新容量是旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        @SuppressWarnings("unchecked")
        E[] newElements = (E[]) new Object[newCapacity];
        // 计算旧的底层数组的索引时，必须使用index(index)的方式，因为此时队列的front头部的真实索引可能不是0了
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        // 重置front的索引为0，之前的front的真实索引时根据旧的底层数组的length计算出来的，
        // 现在底层数组的length已经发生改变了，所以需要重新计算新的底层数组的索引
        // 但是为了方便，直接改成0
        front = 0;
    }

    /**
     * 检查是否需要扩容
     * 
     * @param capacity 需要检查的容量
     * @return 如果当前容量足够则返回true
     */
    protected boolean checkCapacity(int capacity) {
        return elements.length >= capacity;
    }

    /**
     * 缩容队列底层数组
     * 当元素数量小于等于数组容量的一半时，缩容到一半
     */
    protected void shrinking() {
        int oldCapacity = elements.length;
        // 位运算，新容量为之前旧容量的一半
        int newCapacity = oldCapacity >> 1;
        // 如果当前的元素数量大于新容量或者新容量小于默认容量，不需要缩容
        if (size > newCapacity || newCapacity < DEFAULT_CAPACITY) {
            return;
        }

        @SuppressWarnings("unchecked")
        E[] newElements = (E[]) new Object[newCapacity];
        // 计算旧的底层数组的索引时，必须使用index(index)的方式，因为此时队列的front头部的真实索引可能不是0了
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        // 重置front的索引为0，之前的front的真实索引时根据旧的底层数组的length计算出来的，
        // 现在底层数组的length已经发生改变了，所以需要重新计算新的底层数组的索引
        // 但是为了方便，直接改成0
        front = 0;
    }

    /**
     * 返回循环队列的字符串表示
     * 
     * @return 循环队列的详细信息，包括头部信息、大小信息和元素列表
     */
    @Override
    public String toString() {
        // 创建StringBuilder以构建字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 添加size信息
        stringBuilder.append("size: ").append(size).append(", elements: [");
        // 遍历elements数组中的有效元素并添加到字符串中
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(elements[index(i)].toString());
        }
        // 添加elements内容后的"]"和capacity信息
        stringBuilder.append("]")
                .append(", capacity: ")
                .append(elements.length)
                .append(", origin: [");
        // 遍历整个elements数组，包括null值，并添加到字符串中
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(elements[i] == null ? "null" : elements[i].toString());
        }
        // 添加origin数组后的"]"
        stringBuilder.append("]");
        // 返回构建好的字符串
        return stringBuilder.toString();
    }
}
