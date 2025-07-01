package com.hxqzzxk.search;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 二分查找测试基类 - 定义了通用的二分查找测试框架和验证方法
 */
public abstract class BinarySearchTest {
    /**
     * 当前使用的二分查找算法实现
     */
    protected BinarySearch<Integer> binarySearch;

    /**
     * 待查找的数据集合
     */
    protected Integer[] elements;

    /**
     * 查找结果的预期值数组
     */
    protected Integer[] results;

    /**
     * 设置具体的二分查找实现类和测试数据
     */
    @Before
    public abstract void setUp();

    /**
     * 测试查找功能的正确性
     * 遍历所有目标值进行查找，并验证结果是否符合预期
     */
    @Test
    public void testSearch() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= elements.length; i++) {
            list.add(binarySearch.search(elements, i));
        }
        Assert.assertArrayEquals(results, list.toArray());
    }
}
