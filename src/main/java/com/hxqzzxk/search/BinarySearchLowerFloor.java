package com.hxqzzxk.search;

/**
 * lowerFloor 二分搜索实现类
 * 小于 target 时，获取小于 target 的最大值的索引
 * 等于 target 时，获取所有 target 中最小的索引
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BinarySearchLowerFloor<E extends Comparable<E>> implements BinarySearch<E> {
    /**
     * 小于 target 时，返回小于 target 的最大值的索引
     * 等于 target 时，返回所有 target 中最小的索引
     *
     * @param elements 要搜索的有序数组
     * @param target   要查找的目标值
     * @return 小于 target 时，返回小于 target 的最大值的索引，等于 target 时，返回所有 target 中最小的索引
     */
    @Override
    public int search(E[] elements, E target) {
        int left = lower(elements, target);
        if (left + 1 < elements.length && elements[left + 1].compareTo(target) == 0) {
            return left + 1;
        }
        return left;
    }

    /**
     * 在指定的有序数组中查找小于 target 的最大值的索引。
     *
     * @param elements 要搜索的有序数组
     * @param target   要查找的目标值
     * @return 返回小于 target 的最大值的索引
     */
    private int lower(E[] elements, E target) {
        int left = -1;
        int right = elements.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (elements[mid].compareTo(target) < 0) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
