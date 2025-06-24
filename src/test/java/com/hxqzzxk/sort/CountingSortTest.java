package com.hxqzzxk.sort;

/**
 * 计数排序实现类 CountingSort 的测试类，继承自 SortTest
 */
public class CountingSortTest extends SortTest {
    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new CountingSort();
    }
}
