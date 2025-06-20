package com.hxqzzxk.list;

/**
 * Stack 栈数据结构测试接口 - 定义了栈实现的基本测试方法
 */
public interface StackTest {

    /**
     * 测试入栈操作的方法
     */
    void testPush();

    /**
     * 测试出栈操作的方法
     */
    void testPop();

    /**
     * 测试获取栈顶元素的方法
     */
    void testTop();

    /**
     * 测试清空栈的方法
     */
    void testClear();

    /**
     * 测试获取栈中元素数量的方法
     */
    void testSize();

    /**
     * 测试判断栈是否为空的方法
     */
    void testIsEmpty();
}