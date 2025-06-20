package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 双向循环链表测试类
 * 测试双向循环链表实现的线性表功能
 */
public class DoubleCircleLinkedListTest extends AbstractListTest {

    /**
     * 创建测试用的双向循环链表实例
     */
    @Before
    public void createList() {
        list = new DoubleCircleLinkedList<>();
    }

    /**
     * 测试 toString 方法
     * 验证双向循环链表内部的正确性
     */
    @Test
    @Override
    public void testToString() {
        Assert.assertEquals("head: null, tail: null, size: 0, elements: []",
                list.toString());
        list.add(0);
        Assert.assertEquals("head: (0, 0, 0), tail: (0, 0, 0), " +
                        "size: 1, elements: [(0, 0, 0)]",
                list.toString());
        list.add(1);
        Assert.assertEquals("head: (1, 0, 1), tail: (0, 1, 0), size: 2," +
                " elements: [(1, 0, 1), (0, 1, 0)]", list.toString());
        list.add(2);
        Assert.assertEquals("head: (2, 0, 1), tail: (1, 2, 0), size: 3," +
                " elements: [(2, 0, 1), (0, 1, 2), (1," +
                " 2, 0)]", list.toString());
    }
}
