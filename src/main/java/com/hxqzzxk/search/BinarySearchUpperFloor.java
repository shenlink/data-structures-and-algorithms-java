package com.hxqzzxk.search;

/**
 * upperFloor 二分搜索实现类
 * 获取小于等于 target 的最大的索引
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BinarySearchUpperFloor<E extends Comparable<E>> implements BinarySearch<E> {
    /**
     * 在指定的有序数组中查找小于等于 target 的最大的索引
     *
     * @param elements 要搜索的有序数组
     * @param target   要查找的目标值
     * @return 返回获取小于等于 target 的最大的索引
     */
    @Override
    public int search(E[] elements, E target) {
        int left = -1;
        int right = elements.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (elements[mid].compareTo(target) <= 0) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}