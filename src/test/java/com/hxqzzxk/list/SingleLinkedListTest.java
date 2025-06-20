package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 单向链表测试类
 * 测试单向链表实现的线性表功能
 */
public class SingleLinkedListTest extends AbstractListTest {

    /**
     * 创建测试用的单向链表实例
     */
    @Before
    public void createList() {
        list = new SingleLinkedList<>();
    }

    /**
     * 测试 toString 方法
     * 验证单向链表内部的正确性
     */
    @Test
    @Override
    public void testToString() {
        Assert.assertEquals("head: null, size: 0, elements: []",
                list.toString());
        list.add(0);
        Assert.assertEquals("head: (0, null), size: 1, elements: [(0, null)]",
                list.toString());
        list.add(1);
        Assert.assertEquals("head: (0, 1), size: 2, elements: [(0, 1), (1, " +
                "null)]", list.toString());
        list.add(2);
        Assert.assertEquals("head: (0, 1), size: 3, elements: [(0, 1), (1, 2)" +
                ", (2, null)]", list.toString());
    }
}
