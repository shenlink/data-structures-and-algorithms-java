package com.hxqzzxk.search;

/**
 * 基础的二分搜索的另外一种实现
 * 该类基于 lowerCeil 二分搜索的思路实现基础的二分搜索
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BinarySearchAnother<E extends Comparable<E>> implements BinarySearch<E> {
    /**
     * 在指定的有序数组中查找目标元素。
     *
     * @param elements 要搜索的有序数组
     * @param target   要查找的目标值
     * @return 如果找到目标则返回其索引位置，否则返回 -1
     */
    @Override
    public int search(E[] elements, E target) {
        int left = 0;
        int right = elements.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (elements[mid].compareTo(target) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left < elements.length && elements[left].compareTo(target) == 0) {
            return left;
        }
        return -1;
    }
}
