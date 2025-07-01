package com.hxqzzxk.search;

/**
 * 基础的二分搜索的另一种实现
 * 该类提供了另一种实现二分搜索的方式，与基础实现略有不同。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BinarySearchAlt<E extends Comparable<E>> implements BinarySearch<E> {
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
            if (elements[mid].equals(target)) {
                return mid;
            }
            if (elements[mid].compareTo(target) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }
}
