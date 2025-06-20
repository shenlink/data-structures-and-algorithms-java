package com.hxqzzxk.list;

/**
 * 栈数据结构接口
 * 定义栈的基本操作
 * @param <E> 元素类型
 */
public interface Stack<E> {

    /**
     * 入栈操作
     *
     * @param element 要入栈的元素
     */
    void push(E element);

    /**
     * 出栈操作
     *
     * @return 栈顶元素
     */
    E pop();

    /**
     * 获取栈顶元素
     *
     * @return 栈顶元素
     */
    E top();

    /**
     * 清空栈
     */
    void clear();

    /**
     * 获取栈中元素的数量
     *
     * @return 元素数量
     */
    int size();

    /**
     * 检查栈是否为空
     *
     * @return 如果栈为空则返回 true
     */
    boolean isEmpty();
}