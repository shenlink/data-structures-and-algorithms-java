package com.hxqzzxk.search;

/**
 * BinarySearchLowerFloor 测试类
 */
public class BinarySearchLowerFloorTest extends BinarySearchTest {
    /**
     * 初始化 BinarySearchLowerFloor 实例和测试数据
     */
    @Override
    public void setUp() {
        binarySearch = new BinarySearchLowerFloor<>();
        elements = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        results = new Integer[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
