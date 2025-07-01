package com.hxqzzxk.search;

/**
 * BinarySearchUpperCeil 测试类
 */
public class BinarySearchUpperCeilTest extends BinarySearchTest {
    /**
     * 初始化 BinarySearchUpperCeil 实例和测试数据
     */
    @Override
    public void setUp() {
        binarySearch = new BinarySearchUpperCeil<>();
        elements = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        results = new Integer[]{0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
