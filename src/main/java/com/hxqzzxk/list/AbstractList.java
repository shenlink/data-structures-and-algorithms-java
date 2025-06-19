package com.hxqzzxk.list;

/**
 * 抽取线性表重复的操作，为具体的线性表实现类提供基础功能的通用实现
 */
public abstract class AbstractList<E> implements List<E> {
    /**
     * 找不到元素返回的索引值
     */
    protected static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 元素数量
     */
    protected int size;

    /**
     * 返回当前元素的数量
     * 
     * @return 元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 检查线性表是否为空
     * 
     * @return 如果线性表没有元素则返回 true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断线性表中是否包含指定元素
     * 
     * @param element 要查找的元素
     * @return 如果找到元素则返回 true
     */
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 将元素添加到线性表尾部
     * 
     * @param element 要添加的元素
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 确认索引没有越界
     * 
     * @param index 要检查的索引位置
     */
    protected void checkIndex(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    /**
     * 针对添加操作，确认索引没有越界，添加操作可以在线性表的最后位置添加元素
     * 
     * @param index 要检查的索引位置
     */
    protected void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    /**
     * 抛出索引越界异常
     * 
     * @param index 请求访问的位置
     */
    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }
}
