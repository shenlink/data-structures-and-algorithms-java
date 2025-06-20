package com.hxqzzxk.list;

/**
 * 栈数据结构的抽象实现类
 * 提供基于线性表的栈操作的默认实现，适用于基于不同线性表结构构建的栈
 * 作为栈接口的基础适配器，为具体实现类提供复用逻辑
 * 
 * @param <E> 元素类型
 */
public abstract class AbstractStack<E> implements Stack<E> {
    /**
     * 栈的底层数据结构-线性表
     */
    protected List<E> list;

    /**
     * 入栈操作
     * 
     * @param element 要入栈的元素
     */
    public void push(E element) {
        list.add(element);
    }

    /**
     * 出栈操作
     * 
     * @return 栈顶元素
     */
    public E pop() {
        return list.remove(list.size() - 1);
    }

    /**
     * 获取栈顶元素
     * 
     * @return 栈顶元素
     */
    public E top() {
        return list.get(list.size() - 1);
    }

    /**
     * 清空栈
     */
    public void clear() {
        list.clear();
    }

    /**
     * 获取栈中元素的数量
     * 
     * @return 元素数量
     */
    public int size() {
        return list.size();
    }

    /**
     * 检查栈是否为空
     * 
     * @return 如果栈为空则返回 true
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
