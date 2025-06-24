package com.hxqzzxk.sort;

/**
 * 冒泡排序改进版本 BubbleSortLastSwap 的测试类，继承自 SortTest
 */
public class BubbleSortLastSwapTest extends SortTest {
    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new BubbleSortLastSwap<>();
    }
}
