package com.hxqzzxk.search;

/**
 * BinarySearchUpper 测试类
 */
public class BinarySearchUpperTest extends BinarySearchTest {
    /**
     * 初始化 BinarySearchUpper 实例和测试数据
     */
    @Override
    public void setUp() {
        binarySearch = new BinarySearchUpper<>();
        elements = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        results = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    }
}
