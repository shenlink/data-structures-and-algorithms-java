package com.hxqzzxk.list;

/**
 * 基于单向链表实现的栈数据结构
 * 该类利用 {@link SingleLinkedList} 作为底层容器，提供高效的栈操作实现
 * 支持常见的栈操作
 * 
 * @param <E> 元素类型
 */
public class LinkedListStack<E> extends AbstractStack<E> {
    public LinkedListStack() {
        list = new SingleLinkedList<>();
    }
}
