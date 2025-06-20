package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 循环队列测试类
 * 测试基于循环数组实现的队列功能
 */
public class CircleQueueTest {

    private CircleQueue<Integer> queue;

    /**
     * 初始化测试用的循环队列
     */
    @Before
    public void setUp() {
        queue = new CircleQueue<>();
    }

    /**
     * 测试入队操作
     * 验证元素能否正确添加到队列尾部
     */
    @Test
    public void testEnQueue() {
        queue.enQueue(1);
        Assert.assertEquals(Integer.valueOf(1), queue.front());
        Assert.assertEquals(1, queue.size());
        queue.clear();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 3; i++) {
            queue.deQueue();
        }
        queue.enQueue(10);
        queue.enQueue(11);
        Assert.assertEquals("size: 9, elements: [3, 4, 5, 6, 7, 8, 9, 10, " + "11], capacity: 10, origin: [10, 11, null, 3, 4, 5, 6, 7, 8, " + "9]", queue.toString());
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
        Assert.assertEquals(Integer.valueOf(2), queue.front());
        Assert.assertEquals(1, queue.size());
        queue.clear();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 3; i++) {
            queue.deQueue();
        }
        Assert.assertEquals("size: 7, elements: [3, 4, 5, 6, 7, 8, 9], " +
                "capacity: 10, origin: [null, null, null, 3, 4, 5, 6, 7, 8, " + "9]", queue.toString());
    }

    /**
     * 测试获取队头元素操作
     * 验证能否正确获取队列头部元素
     */
    @Test
    public void testFront() {
        queue.enQueue(1);
        Assert.assertEquals(Integer.valueOf(1), queue.front());
        queue.clear();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 3; i++) {
            queue.deQueue();
        }
        Assert.assertEquals(Integer.valueOf(3), queue.front());
        Assert.assertEquals("size: 7, elements: [3, 4, 5, 6, 7, 8, 9], " +
                "capacity: 10, origin: [null, null, null, 3, 4, 5, 6, 7, 8, " + "9]", queue.toString());
    }

    /**
     * 测试清空队列操作
     * 验证队列能否被正确清空
     */
    @Test
    public void testClear() {
        Assert.assertTrue(queue.isEmpty());
        queue.enQueue(1);
        queue.enQueue(2);
        queue.clear();
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        queue.deQueue();
        queue.deQueue();
        queue.clear();
        Assert.assertEquals("size: 0, elements: [], capacity: 10, origin: " +
                "[null, null, null, null, null, null, null, null, null, " +
                "null]", queue.toString());
    }

    /**
     * 测试获取队列大小操作
     * 验证能否正确获取队列中元素的数量
     */
    @Test
    public void testSize() {
        Assert.assertEquals(0, queue.size());
        queue.enQueue(1);
        Assert.assertEquals(1, queue.size());
        queue.enQueue(2);
        Assert.assertEquals(2, queue.size());
        queue.deQueue();
        queue.deQueue();
        Assert.assertEquals(0, queue.size());
    }

    /**
     * 测试判断队列是否为空操作
     * 验证能否正确判断队列是否为空
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(queue.isEmpty());
        queue.enQueue(1);
        Assert.assertFalse(queue.isEmpty());
        queue.deQueue();
        Assert.assertTrue(queue.isEmpty());
    }

    /**
     * 测试队列扩容操作
     * 验证队列在元素数量超过容量时能否正确扩容
     */
    @Test
    public void testExpansion() {
        for (int i = 0; i < 15; i++) {
            queue.enQueue(i);
        }
        Assert.assertEquals(15, queue.size());
        queue.clear();
        for (int i = 0; i < 20; i++) {
            queue.enQueue(i);
        }
        Assert.assertEquals(20, queue.size());
        Assert.assertEquals("size: 20, elements: [0, 1, 2, 3, 4, 5, 6, 7, 8, "
                + "9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19], capacity: 22, "
                + "origin: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14," +
                " " + "15, 16, 17, 18, 19, null, null]", queue.toString());
    }

    /**
     * 测试队列缩容操作
     * 验证队列在元素数量减少到一定比例时能否正确缩容
     */
    @Test
    public void testShrinking() {
        for (int i = 0; i < 20; i++) {
            queue.enQueue(i);
        }
        Assert.assertEquals(20, queue.size());
        for (int i = 0; i < 10; i++) {
            queue.deQueue();
        }
        Assert.assertEquals(10, queue.size());
        Assert.assertEquals("size: 10, elements: [10, 11, 12, 13, 14, 15, 16," +
                " 17, 18, 19], capacity: 11, origin: [null, 10, 11, 12, 13, " +
                "14, 15, 16, 17, 18, 19]", queue.toString());
    }

    /**
     * 测试队列字符串表示操作
     * 验证能否正确获取队列的字符串表示
     */
    @Test
    public void testToString() {
        Assert.assertEquals("size: 0, elements: [], capacity: 10, origin: " +
                "[null, null, null, null, null, null, null, null, null, " +
                "null]", queue.toString());
        queue.enQueue(1);
        Assert.assertEquals("size: 1, elements: [1], capacity: 10, origin: " +
                        "[1, null, null, null, null, null, null, null, null, " +
                        "null]",
                queue.toString());
        queue.enQueue(2);
        Assert.assertEquals("size: 2, elements: [1, 2], capacity: 10, origin:" +
                        " [1, 2, null, null, null, null, null, null, null, " +
                        "null]",
                queue.toString());
        for (int i = 3; i < 15; i++) {
            queue.enQueue(i);
        }
        Assert.assertEquals("size: 14, elements: [1, 2, 3, 4, 5, 6, 7, 8, 9, "
                + "10, 11, 12, 13, 14], capacity: 15, origin: [1, 2, 3, 4, 5," +
                " 6, 7, 8, 9, 10, 11, 12, 13, 14, null]", queue.toString());
        queue.deQueue();
        Assert.assertEquals("size: 13, elements: [2, 3, 4, 5, 6, 7, 8, 9, 10," +
                " 11, 12, 13, 14], capacity: 15, origin: [null, 2, 3, 4, 5, " +
                "6, 7, 8, 9, 10, 11, 12, 13, 14, null]", queue.toString());
    }
}
