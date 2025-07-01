package com.hxqzzxk.search;

/**
 * BinarySearchLowerCeil 测试类
 */
public class BinarySearchLowerCeilTest extends BinarySearchTest {
    /**
     * 初始化 BinarySearchLowerCeil 实例和测试数据
     */
    @Override
    public void setUp() {
        binarySearch = new BinarySearchLowerCeil<>();
        elements = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        results = new Integer[]{0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
