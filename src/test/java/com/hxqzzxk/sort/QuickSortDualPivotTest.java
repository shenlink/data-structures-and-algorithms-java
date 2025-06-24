package com.hxqzzxk.sort;

/**
 * 快速排序三路划分版本 QuickSortDualPivot 的测试类，继承自 SortTest
 */
public class QuickSortDualPivotTest extends SortTest {
    /**
     * 排序测试数据规模
     */
    protected int size = 1000000;

    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new QuickSortDualPivot<>();
    }
}
