package com.hxqzzxk.heap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 二叉堆测试类
 * 测试 BinaryHeap 类的各种功能
 */
public class BinaryHeapTest {
    /**
     * 最大堆实例，用于测试最大堆功能
     */
    private BinaryHeap<Integer> maxHeap;

    /**
     * 最小堆实例，用于测试最小堆功能
     */
    private BinaryHeap<Integer> minHeap;

    /**
     * 初始化测试用的最大堆和最小堆实例
     */
    @Before
    public void setUp() {
        // 创建一个最大堆
        maxHeap = new BinaryHeap<>((o1, o2) -> o1 - o2);
        // 创建一个最小堆
        minHeap = new BinaryHeap<>((o1, o2) -> o2 - o1);
    }

    /**
     * 测试清除堆中所有元素操作
     */
    @Test
    public void testClear() {
        Assert.assertEquals(0, maxHeap.size());
        Assert.assertEquals(0, minHeap.size());
        for (int i = 0; i < 100; i++) {
            maxHeap.add(i);
            minHeap.add(i);
        }

        Assert.assertEquals(100, maxHeap.size());
        Assert.assertEquals(100, minHeap.size());
        maxHeap.clear();
        minHeap.clear();
        Assert.assertEquals(0, maxHeap.size());
        Assert.assertEquals(0, minHeap.size());
    }

    /**
     * 测试获取堆中元素数量操作
     */
    @Test
    public void testSize() {
        Assert.assertEquals(0, maxHeap.size());
        Assert.assertEquals(0, minHeap.size());
        for (int i = 0; i < 100; i++) {
            maxHeap.add(i);
            minHeap.add(i);
        }
        Assert.assertEquals(100, maxHeap.size());
        Assert.assertEquals(100, minHeap.size());
    }

    /**
     * 测试判断堆是否为空操作
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(maxHeap.isEmpty());
        Assert.assertTrue(minHeap.isEmpty());
        for (int i = 0; i < 100; i++) {
            maxHeap.add(i);
            minHeap.add(i);
        }
        Assert.assertFalse(maxHeap.isEmpty());
        Assert.assertFalse(minHeap.isEmpty());
        maxHeap.clear();
        minHeap.clear();
        Assert.assertTrue(maxHeap.isEmpty());
        Assert.assertTrue(minHeap.isEmpty());
    }

    /**
     * 测试向堆中添加元素操作
     */
    @Test
    public void testAdd() {
        for (int i = 0; i < 100; i++) {
            maxHeap.add(i);
            minHeap.add(i);
        }
        Assert.assertEquals(100, maxHeap.size());
        Assert.assertEquals(100, minHeap.size());
        Assert.assertEquals(Integer.valueOf(99), maxHeap.get());
        Assert.assertEquals(Integer.valueOf(0), minHeap.get());
    }

    /**
     * 测试获取堆顶元素但不移除它
     */
    @Test
    public void testGet() {
        for (int i = 0; i < 100; i++) {
            maxHeap.add(i);
            minHeap.add(i);
        }
        Assert.assertEquals(Integer.valueOf(99), maxHeap.get());
        Assert.assertEquals(Integer.valueOf(0), minHeap.get());
    }

    /**
     * 测试移除并返回堆顶元素操作
     */
    @Test
    public void testRemove() {
        for (int i = 0; i < 100; i++) {
            maxHeap.add(i);
            minHeap.add(i);
        }
        Assert.assertEquals(100, maxHeap.size());
        Assert.assertEquals(100, minHeap.size());
        for (int i = 99; i >= 0; i--) {
            Assert.assertEquals(Integer.valueOf(i), maxHeap.remove());
        }
        for (int i = 0; i < 100; i++) {
            Assert.assertEquals(Integer.valueOf(i), minHeap.remove());
        }
        Assert.assertEquals(0, maxHeap.size());
        Assert.assertEquals(0, minHeap.size());
    }

    /**
     * 测试替换堆顶元素并返回旧元素操作
     */
    @Test
    public void testReplace() {
        for (int i = 0; i < 100; i++) {
            maxHeap.add(i);
            minHeap.add(i);
        }
        Assert.assertEquals(Integer.valueOf(99), maxHeap.replace(100));
        Assert.assertEquals(Integer.valueOf(0), minHeap.replace(-1));
        Assert.assertEquals(Integer.valueOf(100), maxHeap.get());
        Assert.assertEquals(Integer.valueOf(-1), minHeap.get());
    }

    /**
     * 测试使用元素数组批量构建堆的操作
     */
    @Test
    public void testHeapify() {
        Integer[] elements = new Integer[100];
        for (int i = 0; i < 100; i++) {
            elements[i] = i;
        }
        maxHeap = new BinaryHeap<>(elements, (o1, o2) -> o1 - o2);
        minHeap = new BinaryHeap<>(elements, (o1, o2) -> o2 - o1);

        Assert.assertEquals(100, maxHeap.size());
        Assert.assertEquals(100, minHeap.size());
        for (int i = 99; i >= 0; i--) {
            Assert.assertEquals(Integer.valueOf(i), maxHeap.remove());
        }
        for (int i = 0; i < 100; i++) {
            Assert.assertEquals(Integer.valueOf(i), minHeap.remove());
        }
        Assert.assertEquals(0, maxHeap.size());
        Assert.assertEquals(0, minHeap.size());
    }

    /**
     * 测试不能添加null元素到堆中的异常处理
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullElement() {
        minHeap.add(null);
    }

    /**
     * 测试从空堆中获取堆顶元素时的异常处理
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFromEmptyHeap() {
        minHeap.get();
    }

    /**
     * 测试从空堆中移除堆顶元素时的异常处理
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveFromEmptyHeap() {
        minHeap.remove();
    }
}