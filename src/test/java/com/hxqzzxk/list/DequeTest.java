package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 双端队列接口测试类
 * 测试双端队列的基本操作功能
 */
public class DequeTest {

    private Deque<Integer> deque;

    /**
     * 初始化测试用的通用双端队列
     */
    @Before
    public void setUp() {
        deque = new Deque<>();
    }

    /**
     * 测试从队尾入队操作
     * 验证元素能否正确添加到队列尾部
     */
    @Test
    public void testEnQueueRear() {
        deque.enQueueRear(1);
        Assert.assertEquals(Integer.valueOf(1), deque.rear());
        Assert.assertEquals(Integer.valueOf(1), deque.front());
    }

    /**
     * 测试从队首出队操作
     * 验证元素能否正确从队列头部移除
     */
    @Test
    public void testDeQueueFront() {
        deque.enQueueRear(1);
        deque.enQueueRear(2);
        Assert.assertEquals(Integer.valueOf(1), deque.deQueueFront());
        Assert.assertEquals(Integer.valueOf(2), deque.front());
    }

    /**
     * 测试从队首入队操作
     * 验证元素能否正确添加到队列头部
     */
    @Test
    public void testEnQueueFront() {
        deque.enQueueFront(1);
        deque.enQueueFront(2);
        Assert.assertEquals(Integer.valueOf(2), deque.front());
        Assert.assertEquals(Integer.valueOf(1), deque.rear());
    }

    /**
     * 测试从队尾出队操作
     * 验证元素能否正确从队列尾部移除
     */
    @Test
    public void testDeQueueRear() {
        deque.enQueueRear(1);
        deque.enQueueRear(2);
        Assert.assertEquals(Integer.valueOf(2), deque.deQueueRear());
        Assert.assertEquals(Integer.valueOf(1), deque.rear());
    }

    /**
     * 测试获取队首元素操作
     * 验证能否正确获取队列头部元素
     */
    @Test
    public void testFront() {
        deque.enQueueFront(1);
        Assert.assertEquals(Integer.valueOf(1), deque.front());
    }

    /**
     * 测试获取队尾元素操作
     * 验证能否正确获取队列尾部元素
     */
    @Test
    public void testRear() {
        deque.enQueueRear(1);
        Assert.assertEquals(Integer.valueOf(1), deque.rear());
        deque.enQueueRear(2);
        Assert.assertEquals(Integer.valueOf(2), deque.rear());
    }

    /**
     * 测试清空队列操作
     * 验证能否正确清空队列
     */
    @Test
    public void testClear() {
        Assert.assertTrue(deque.isEmpty());
        deque.enQueueRear(1);
        deque.enQueueRear(2);
        deque.clear();
        Assert.assertTrue(deque.isEmpty());
    }

    /**
     * 测试获取队列大小操作
     * 验证能否正确获取队列大小
     */
    @Test
    public void testSize() {
        Assert.assertEquals(0, deque.size());
        deque.enQueueRear(1);
        Assert.assertEquals(1, deque.size());
        deque.enQueueRear(2);
        Assert.assertEquals(2, deque.size());
    }

    /**
     * 测试判断队列是否为空操作
     * 验证能否正确判断队列是否为空
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(deque.isEmpty());
        deque.enQueueRear(1);
        Assert.assertFalse(deque.isEmpty());
    }
}
