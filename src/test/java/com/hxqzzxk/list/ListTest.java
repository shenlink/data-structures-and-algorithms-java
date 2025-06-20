package com.hxqzzxk.list;

/**
 * List 线性表测试接口 - 定义了线性表实现的基本测试方法
 */
public interface ListTest {

    /**
     * 测试清空线性表的方法
     */
    void testClear();

    /**
     * 测试获取线性表元素数量的方法
     */
    void testSize();

    /**
     * 测试判断线性表是否为空的方法
     */
    void testIsEmpty();

    /**
     * 测试判断线性表是否包含指定元素的方法
     */
    void testContains();

    /**
     * 测试向线性表尾部添加元素的方法
     */
    void testAdd();

    /**
     * 测试获取线性表指定位置元素的方法
     */
    void testGet();

    /**
     * 测试设置线性表指定位置元素的方法
     */
    void testSet();

    /**
     * 测试在线性表指定位置插入元素的方法
     */
    void testAddAtIndex();

    /**
     * 测试删除线性表指定位置元素的方法
     */
    void testRemove();

    /**
     * 测试查找线性表中指定元素位置的方法
     */
    void testIndexOf();

    /**
     * 测试索引越界异常处理
     */
    void testCheckIndex();

    /**
     * 测试针对 add 操作的索引越界异常处理
     */
    void testCheckIndexForAdd();

    /**
     * 测试线性表的 toString 方法，验证内部状态的正确性
     */
    void testToString();
}
