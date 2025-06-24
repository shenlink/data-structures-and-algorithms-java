package com.hxqzzxk.sort;

/**
 * 希尔排序实现类 ShellSort 的测试类，继承自 SortTest
 */
public class ShellSortTest extends SortTest {
    /**
     * 初始化待测试的排序算法实例
     */
    @Override
    public void setUp() {
        sort = new ShellSort<>();
    }
}
