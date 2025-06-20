package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 单向循环链表测试类
 * 测试单向循环链表实现的线性表功能
 */
public class SingleCircleLinkedListTest extends AbstractListTest {

    /**
     * 创建测试用的单向循环链表实例
     */
    @Before
    public void createList() {
        list = new SingleCircleLinkedList<>();
    }

    /**
     * 测试 toString 方法
     * 验证单向循环链表内部的正确性
     */
    @Test
    @Override
    public void testToString() {
        Assert.assertEquals("head: null, size: 0, elements: []",
                list.toString());
        list.add(0);
        Assert.assertEquals("head: (0, 0), size: 1, elements: [(0, 0)]",
                list.toString());
        list.add(1);
        Assert.assertEquals("head: (0, 1), size: 2, elements: [(0, 1), (1, 0)]"
                , list.toString());
        list.add(2);
        Assert.assertEquals("head: (0, 1), size: 3, elements: [(0, 1), (1, 2)" +
                ", (2, 0)]", list.toString());
    }
}
