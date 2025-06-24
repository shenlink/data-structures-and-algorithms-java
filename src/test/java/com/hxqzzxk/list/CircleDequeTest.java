package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 双端队列测试类
 * 测试基于循环数组实现的双端队列功能
 */
public class CircleDequeTest {

    private CircleDeque<Integer> deque;

    /**
     * 初始化测试用的循环双端队列
     */
    @Before
    public void setUp() {
        deque = new CircleDeque<>();
    }

    /**
     * 测试从队尾入队操作
     * 验证元素能否正确添加到队列尾部
     */
    @Test
    public void testEnQueueRear() {
        deque.enQueueRear(1);
        Assert.assertEquals(Integer.valueOf(1), deque.front());
        Assert.assertEquals(1, deque.size());
        deque.enQueueRear(2);
        Assert.assertEquals(Integer.valueOf(1), deque.front());
        Assert.assertEquals(Integer.valueOf(2), deque.rear());
        Assert.assertEquals(2, deque.size());

        deque.clear();
        deque.enQueueRear(1);
        Assert.assertEquals(Integer.valueOf(1), deque.front());
        Assert.assertEquals(1, deque.size());
        deque.clear();
        for (int i = 0; i < 10; i++) {
            deque.enQueueRear(i);
        }
        for (int i = 0; i < 3; i++) {
            deque.deQueueFront();
        }
        deque.enQueueRear(10);
        deque.enQueueRear(11);
        Assert.assertEquals("size: 9, elements: [3, 4, 5, 6, 7, 8, 9, 10, "
                + "11], capacity: 10, origin: [10, 11, null, 3, 4, 5, 6, 7, " +
                "8, " + "9]", deque.toString());
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
        Assert.assertEquals(1, deque.size());

        Assert.assertEquals(Integer.valueOf(2), deque.deQueueFront());
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(0, deque.size());

        deque.enQueueRear(1);
        deque.enQueueRear(2);
        Assert.assertEquals(Integer.valueOf(2), deque.rear());
        Assert.assertEquals(Integer.valueOf(1), deque.deQueueFront());
        Assert.assertEquals(Integer.valueOf(2), deque.front());
        Assert.assertEquals(1, deque.size());
        deque.clear();
        for (int i = 0; i < 10; i++) {
            deque.enQueueRear(i);
        }
        for (int i = 0; i < 3; i++) {
            deque.deQueueFront();
        }
        Assert.assertEquals("size: 7, elements: [3, 4, 5, 6, 7, 8, 9], " +
                "capacity: 10, origin: [null, null, null, 3, 4, 5, 6, 7, 8, " + "9]", deque.toString());
    }

    /**
     * 测试从队首入队操作
     * 验证元素能否正确添加到队列头部
     */
    @Test
    public void testEnQueueFront() {
        deque.enQueueFront(1);
        Assert.assertEquals(Integer.valueOf(1), deque.front());
        Assert.assertEquals(1, deque.size());
        deque.enQueueFront(2);
        Assert.assertEquals(Integer.valueOf(2), deque.front());
        Assert.assertEquals(Integer.valueOf(1), deque.rear());
        Assert.assertEquals(2, deque.size());

        deque.clear();
        deque.enQueueFront(1);
        Assert.assertEquals(Integer.valueOf(1), deque.front());
        Assert.assertEquals(1, deque.size());

        deque.clear();
        for (int i = 9; i >= 0; i--) {
            deque.enQueueFront(i);
        }
        for (int i = 0; i < 3; i++) {
            deque.deQueueRear();
        }
        deque.enQueueFront(10);
        deque.enQueueFront(11);
        Assert.assertEquals("size: 9, elements: [11, 10, 0, 1, 2, 3, 4, 5, " +
                "6], capacity: 10, origin: [0, 1, 2, 3, 4, 5, 6, null, 11, " +
                "10]", deque.toString());
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
        Assert.assertEquals(Integer.valueOf(1), deque.front());
        Assert.assertEquals(1, deque.size());
        Assert.assertEquals(Integer.valueOf(1), deque.deQueueRear());
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(0, deque.size());

        deque.clear();
        deque.enQueueFront(1);
        deque.enQueueFront(2);
        Assert.assertEquals(Integer.valueOf(1), deque.deQueueRear());
        Assert.assertEquals(Integer.valueOf(2), deque.front());
        Assert.assertEquals(1, deque.size());

        deque.clear();
        for (int i = 0; i < 10; i++) {
            deque.enQueueRear(i);
        }
        for (int i = 0; i < 3; i++) {
            deque.deQueueRear();
        }
        Assert.assertEquals("size: 7, elements: [0, 1, 2, 3, 4, 5, 6], " +
                "capacity: 10, origin: [0, 1, 2, 3, 4, 5, 6, null, null, " +
                "null]", deque.toString());
    }

    /**
     * 测试获取队首元素操作
     * 验证能否正确获取队列头部元素
     */
    @Test
    public void testFront() {
        deque.enQueueRear(1);
        Assert.assertEquals(Integer.valueOf(1), deque.front());

        deque.clear();
        deque.enQueueRear(1);
        Assert.assertEquals(Integer.valueOf(1), deque.front());

        deque.clear();
        for (int i = 0; i < 10; i++) {
            deque.enQueueRear(i);
        }
        for (int i = 0; i < 3; i++) {
            deque.deQueueFront();
        }
        Assert.assertEquals(Integer.valueOf(3), deque.front());
        Assert.assertEquals("size: 7, elements: [3, 4, 5, 6, 7, 8, 9], " +
                "capacity: 10, origin: [null, null, null, 3, 4, 5, 6, 7, 8, " + "9]", deque.toString());
    }

    /**
     * 测试获取队尾元素操作
     * 验证能否正确获取队列尾部元素
     */
    @Test
    public void testRear() {
        deque.enQueueRear(1);
        deque.enQueueRear(2);
        Assert.assertEquals(Integer.valueOf(2), deque.rear());

        deque.clear();
        deque.enQueueRear(1);
        Assert.assertEquals(Integer.valueOf(1), deque.front());
        deque.clear();
        for (int i = 0; i < 10; i++) {
            deque.enQueueRear(i);
        }
        for (int i = 0; i < 3; i++) {
            deque.deQueueFront();
        }
        Assert.assertEquals(Integer.valueOf(3), deque.front());
        Assert.assertEquals("size: 7, elements: [3, 4, 5, 6, 7, 8, 9], " +
                "capacity: 10, origin: [null, null, null, 3, 4, 5, 6, 7, 8, " + "9]", deque.toString());
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
        Assert.assertEquals(0, deque.size());

        deque.clear();
        Assert.assertTrue(deque.isEmpty());
        deque.enQueueFront(1);
        deque.enQueueFront(2);
        deque.clear();
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(0, deque.size());
        for (int i = 0; i < 10; i++) {
            deque.enQueueFront(i);
        }
        deque.deQueueFront();
        deque.deQueueFront();
        deque.clear();
        Assert.assertEquals("size: 0, elements: [], capacity: 10, origin: " +
                "[null, null, null, null, null, null, null, null, null, " +
                "null]", deque.toString());
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
        deque.deQueueFront();
        deque.deQueueFront();
        Assert.assertEquals(0, deque.size());
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
        deque.deQueueFront();
        Assert.assertTrue(deque.isEmpty());
    }

    /**
     * 测试队列扩容操作
     * 验证队列在元素数量超过容量时能否正确扩容
     */
    @Test
    public void testExpansion() {
        for (int i = 0; i < 15; i++) {
            deque.enQueueRear(i);
        }
        Assert.assertEquals(15, deque.size());
        deque.clear();
        for (int i = 0; i < 20; i++) {
            deque.enQueueRear(i);
        }
        Assert.assertEquals(20, deque.size());
        Assert.assertEquals("size: 20, elements: [0, 1, 2, 3, 4, 5, 6, 7, 8, "
                + "9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19], capacity: 22, "
                + "origin: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14," +
                " " + "15, 16, 17, 18, 19, null, null]", deque.toString());
    }

    /**
     * 测试队列缩容操作
     * 验证队列在元素数量减少到一定程度时能否正确缩容
     */
    @Test
    public void testShrinking() {
        for (int i = 0; i < 20; i++) {
            deque.enQueueRear(i);
        }
        Assert.assertEquals(20, deque.size());
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 null null null
        for (int i = 0; i < 10; i++) {
            deque.deQueueRear();
        }
        Assert.assertEquals(10, deque.size());
        Assert.assertEquals("size: 10, elements: [0, 1, 2, 3, 4, 5, 6, 7, 8, " +
                "9], capacity: 11, origin: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, " +
                "null]", deque.toString());
    }

    /**
     * 测试队列字符串表示操作
     * 验证能否正确获取队列的字符串表示
     */
    @Test
    public void testToString() {
        Assert.assertEquals("size: 0, elements: [], capacity: 10, origin: " +
                "[null, null, null, null, null, null, null, null, null, " +
                "null]", deque.toString());
        deque.enQueueRear(1);
        Assert.assertEquals("size: 1, elements: [1], capacity: 10, origin: " +
                        "[1, null, null, null, null, null, null, null, null, " +
                        "null]",
                deque.toString());
        deque.enQueueRear(2);
        Assert.assertEquals("size: 2, elements: [1, 2], capacity: 10, origin:" +
                        " [1, 2, null, null, null, null, null, null, null, " +
                        "null]",
                deque.toString());
        for (int i = 3; i < 15; i++) {
            deque.enQueueRear(i);
        }
        Assert.assertEquals("size: 14, elements: [1, 2, 3, 4, 5, 6, 7, 8, 9, "
                + "10, 11, 12, 13, 14], capacity: 15, origin: [1, 2, 3, 4, 5," +
                " 6, 7, 8, 9, 10, 11, 12, 13, 14, null]", deque.toString());
        deque.deQueueFront();
        Assert.assertEquals("size: 13, elements: [2, 3, 4, 5, 6, 7, 8, 9, 10," +
                " 11, 12, 13, 14], capacity: 15, origin: [null, 2, 3, 4, 5, " +
                "6, 7, 8, 9, 10, 11, 12, 13, 14, null]", deque.toString());
        deque.deQueueRear();
        Assert.assertEquals("size: 12, elements: [2, 3, 4, 5, 6, 7, 8, 9, 10," +
                " 11, 12, 13], capacity: 15, origin: [null, 2, 3, 4, 5, " +
                "6, 7, 8, 9, 10, 11, 12, 13, null, null]", deque.toString());
    }

}
