package com.hxqzzxk.list;

import org.junit.Before;

/**
 * LinkedListStack 单元测试类
 * 针对基于单向链表实现的栈结构进行功能验证
 * 继承自 AbstractStackTest，提供具体的栈实例创建逻辑
 */
public class LinkedListStackTest extends AbstractStackTest {

    /**
     * 创建一个 LinkedListStack 实例
     */
    @Before
    public void createStack() {
        stack = new LinkedListStack<>();
    }
}