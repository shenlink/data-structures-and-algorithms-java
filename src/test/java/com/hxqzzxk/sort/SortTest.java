package com.hxqzzxk.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * 排序算法测试基类 - 定义了通用的排序测试框架和验证方法
 */
public abstract class SortTest {
    /**
     * 当前使用的排序算法实现
     */
    protected Sort<Integer> sort;

    /**
     * 测试数组大小，默认为100个元素
     */
    protected int size = 100;

    /**
     * 待排序的数据集合
     */
    protected Integer[] elements;

    /**
     * 初始化测试数据集
     * 创建指定大小的随机整数数组用于排序测试
     */
    @Before
    public void prepare() {
        int n = size;
        elements = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++)
            elements[i] = random.nextInt(n);
    }

    /**
     * 设置具体的排序实现类
     */
    @Before
    public abstract void setUp();

    /**
     * 测试排序功能的正确性
     * 验证排序后的数组是否按升序排列
     */
    @Test
    public void testSort() {
        sort.sort(elements);
        Assert.assertTrue(isSorted());
    }

    /**
     * 辅助方法验证数组是否已排序
     * 
     * @return 如果数组有序返回 true，否则返回 false
     */
    protected boolean isSorted() {
        for (int i = 0; i < elements.length - 1; i++) {
            if (elements[i] > elements[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
