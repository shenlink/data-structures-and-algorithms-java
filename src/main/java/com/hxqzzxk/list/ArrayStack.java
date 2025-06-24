package com.hxqzzxk.list;

/**
 * 基于动态数组实现的栈数据结构
 * 该类利用 {@link ArrayList} 作为底层容器，提供高效的栈操作实现
 * 支持常见的栈操作
 *
 * @param <E> 元素类型
 */
public class ArrayStack<E> extends AbstractStack<E> {
    public ArrayStack() {
        list = new ArrayList<>();
    }
}
