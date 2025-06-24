package com.hxqzzxk.sort;

/**
 * 快速排序尾递归优化版本 QuickSort4 的测试类，继承自 SortTest
 */
public class QuickSort3WayTest extends SortTest {
    // 排序测试数据规模，默认为一百万个元素
    protected int size = 1000000;

    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new QuickSort3Way<>();
    }
}
