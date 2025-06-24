package com.hxqzzxk.sort;

/**
 * 插入排序优化版本 InsertionSort2 的测试类，继承自 SortTest
 */
public class InsertionSortShiftOnlyTest extends SortTest {
    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new InsertionSortShiftOnly<>();
    }

}
