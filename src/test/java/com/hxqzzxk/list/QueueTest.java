package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 队列接口测试类
 * 测试队列(Queue)实现的基本功能
 */
public class QueueTest {
    private Queue<Integer> queue;

    /**
     * 初始化测试用的通用队列
     */
    @Before
    public void setUp() {
        queue = new Queue<>();
    }

    /**
     * 测试入队操作
     * 验证元素能否正确添加到队列尾部
     */
    @Test
    public void testEnQueue() {
        queue.enQueue(1);
        Assert.assertEquals(1, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.front());
    }

    /**
     * 测试出队操作
     * 验证元素能否正确从队列头部移除
     */
    @Test
    public void testDeQueue() {
        queue.enQueue(1);
        queue.enQueue(2);
        Assert.assertEquals(Integer.valueOf(1), queue.deQueue());
        Assert.assertEquals(1, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.front());
    }

    /**
     * 测试获取队头元素操作
     * 验证能否正确获取队列头部元素
     */
    @Test
    public void testFront() {
        queue.enQueue(1);
        Assert.assertEquals(Integer.valueOf(1), queue.front());
    }

    /**
     * 测试获取队头元素操作在空队列上的行为
     * 验证能否正确抛出异常
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testFrontOnEmptyQueue() {
        queue.front();
    }

    /**
     * 测试清空队列操作
     * 验证队列能否正确清空
     */
    @Test
    public void testClear() {
        queue.enQueue(1);
        queue.enQueue(2);
        queue.clear();
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
    }

    /**
     * 测试获取队列大小操作
     * 验证队列大小能否正确返回
     */
    @Test
    public void testSize() {
        Assert.assertEquals(0, queue.size());
        queue.enQueue(1);
        Assert.assertEquals(1, queue.size());
        queue.enQueue(2);
        Assert.assertEquals(2, queue.size());
        queue.deQueue();
        Assert.assertEquals(1, queue.size());
    }

    /**
     * 测试判断队列是否为空操作
     * 验证队列是否为空判断是否正确
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(queue.isEmpty());
        queue.enQueue(1);
        Assert.assertFalse(queue.isEmpty());
        queue.deQueue();
        Assert.assertTrue(queue.isEmpty());
    }
}
