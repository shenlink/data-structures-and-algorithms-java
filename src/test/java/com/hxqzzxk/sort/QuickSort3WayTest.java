package com.hxqzzxk.sort;

/**
 * 快速排序尾递归优化版本 QuickSort3Way 的测试类，继承自 SortTest
 */
public class QuickSort3WayTest extends SortTest {
    /**
     * 排序测试数据规模
     */
    protected int size = 1000000;

    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new QuickSort3Way<>();
    }
}
