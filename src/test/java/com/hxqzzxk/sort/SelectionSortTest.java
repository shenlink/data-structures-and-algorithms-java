package com.hxqzzxk.sort;

/**
 * 选择排序实现类 SelectionSort 的测试类，继承自 SortTest
 */
public class SelectionSortTest extends SortTest {
    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new SelectionSort<>();
    }
}
