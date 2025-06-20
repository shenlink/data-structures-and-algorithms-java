package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 双向链表测试类
 * 测试双向链表实现的线性表功能
 */
public class DoubleLinkedListTest extends AbstractListTest {

    /**
     * 创建测试用的双向链表实例
     */
    @Before
    public void createList() {
        list = new DoubleLinkedList<>();
    }

    /**
     * 测试 toString 方法
     * 验证双向链表内部的正确性
     */
    @Test
    @Override
    public void testToString() {
        Assert.assertEquals("head: null, tail: null, size: 0, elements: []",
                list.toString());
        list.add(0);
        Assert.assertEquals("head: (null, 0, null), tail: (null, 0, null), " +
                        "size: 1, elements: [(null, 0, null)]",
                list.toString());
        list.add(1);
        Assert.assertEquals("head: (null, 0, 1), tail: (0, 1, null), size: 2," +
                        " elements: [(null, 0, 1), (0, 1, null)]"
                , list.toString());
        list.add(2);
        Assert.assertEquals("head: (null, 0, 1), tail: (1, 2, null), size: 3," +
                " elements: [(null, 0, 1), (0, 1, 2), (1," +
                " 2, null)]", list.toString());
    }
}
