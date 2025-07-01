package com.hxqzzxk.search;

/**
 * 基础的二分搜索的递归实现类
 * 该类使用递归方式实现基础的二分搜索算法。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BinarySearchRecursive<E extends Comparable<E>> implements BinarySearch<E> {
    /**
     * 在指定的有序数组中查找目标元素。
     *
     * @param elements 要搜索的有序数组
     * @param target   要查找的目标值
     * @return 如果找到目标则返回其索引位置，否则返回 -1
     */
    @Override
    public int search(E[] elements, E target) {
        return search(elements, 0, elements.length - 1, target);
    }

    private int search(E[] elements, int left, int right, E target) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (elements[mid].compareTo(target) == 0) {
            return mid;
        } else if (elements[mid].compareTo(target) < 0) {
            return search(elements, mid + 1, right, target);
        } else {
            return search(elements, left, mid - 1, target);
        }
    }
}
