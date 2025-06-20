package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 优先队列测试类
 * 测试基于堆实现的优先队列功能
 */
public class PriorityQueueTest {
    /**
     * 优先队列实例
     */
    private PriorityQueue<Integer> queue;

    /**
     * 初始化测试用的优先队列
     */
    @Before
    public void setUp() {
        // 使用默认的自然顺序比较器
        queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
    }

    /**
     * 测试获取队列大小的方法
     */
    @Test
    public void testSize() {
        // 初始状态下队列为空，大小为0
        Assert.assertEquals(0, queue.size());

        // 添加一个元素后，队列大小变为1
        queue.enQueue(10);
        Assert.assertEquals(1, queue.size());

        // 再添加一个元素，队列大小变为2
        queue.enQueue(20);
        Assert.assertEquals(2, queue.size());

        // 移除一个元素后，队列大小减为1
        queue.deQueue();
        Assert.assertEquals(1, queue.size());
    }

    /**
     * 测试检查队列是否为空的方法
     */
    @Test
    public void testIsEmpty() {
        // 初始状态下队列为空
        Assert.assertTrue(queue.isEmpty());

        // 添加一个元素后，队列不再为空
        queue.enQueue(10);
        Assert.assertFalse(queue.isEmpty());

        // 移除一个元素后，队列重新变为空
        queue.deQueue();
        Assert.assertTrue(queue.isEmpty());
    }

    /**
     * 测试清空队列的方法
     */
    @Test
    public void testClear() {
        // 初始状态下队列为空，大小为0
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());

        // 添加几个元素后
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(5);

        // 清空队列
        queue.clear();

        // 清空后队列大小为0且为空
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
    }

    /**
     * 测试向队列中插入元素的方法
     */
    @Test
    public void testEnQueue() {
        // 依次插入100个元素
        for (int i = 0; i < 100; i++) {
            queue.enQueue(i);
        }
        // 验证队列大小是否为100
        Assert.assertEquals(100, queue.size());

        // 依次移除元素并验证移除的顺序是否符合优先级
        for (int i = 99; i >= 0; i--) {
            Assert.assertEquals(Integer.valueOf(i), queue.deQueue());
        }
    }

    /**
     * 测试从队列中移除元素的方法
     */
    @Test
    public void testDeQueue() {
        // 依次插入100个元素
        for (int i = 0; i < 100; i++) {
            queue.enQueue(i);
        }
        // 验证队列大小是否为100
        Assert.assertEquals(100, queue.size());

        // 依次移除元素并验证移除的顺序是否符合优先级
        for (int i = 99; i >= 0; i--) {
            Assert.assertEquals(Integer.valueOf(i), queue.deQueue());
        }

        // 验证队列是否已为空
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
    }

    /**
     * 测试获取队列首部元素的方法
     */
    @Test
    public void testFront() {
        // 依次插入100个元素
        for (int i = 0; i < 100; i++) {
            queue.enQueue(i);
        }

        // 验证队列首部元素是否为优先级最高的元素
        Assert.assertEquals(Integer.valueOf(99), queue.front());
    }
}