package com.hxqzzxk.sort;

/**
 * 快速排序随机化版本 QuickSort3 的测试类，继承自 SortTest
 */
public class QuickSortDualScanTest extends SortTest {
    // 排序测试数据规模，默认为一百万个元素
    protected int size = 1000000;

    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new QuickSortDualScan<>();
    }
}
