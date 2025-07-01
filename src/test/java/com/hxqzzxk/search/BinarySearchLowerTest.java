package com.hxqzzxk.search;

/**
 * BinarySearchLower 测试类
 */
public class BinarySearchLowerTest extends BinarySearchTest {
    /**
     * 初始化 BinarySearchLower 实例和测试数据
     */
    @Override
    public void setUp() {
        binarySearch = new BinarySearchLower<>();
        elements = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        results = new Integer[]{-1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8};
    }
}
