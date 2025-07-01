package com.hxqzzxk.search;

/**
 * upper 二分搜索实现类
 * 获取大于 target 的最小值的索引
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BinarySearchUpper<E extends Comparable<E>> implements BinarySearch<E> {
    /**
     * 在指定的有序数组中查找大于 target 的最小值的索引。
     *
     * @param elements 要搜索的有序数组
     * @param target   要查找的目标值
     * @return 返回大于 target 的最小值的索引
     */
    @Override
    public int search(E[] elements, E target) {
        int left = 0;
        int right = elements.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (elements[mid].compareTo(target) <= 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
