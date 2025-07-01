package com.hxqzzxk.search;

/**
 * 定义搜索算法的公共接口，用于在数据集合中查找目标元素。
 * 该接口为不同的搜索算法实现提供了统一的 API 规范。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public interface BinarySearch<E extends Comparable<E>> {
    /**
     * 在指定的数组中查找目标元素。
     *
     * @param elements 要搜索的数组，必须是有序的（具体依赖实现）
     * @param target   要查找的目标值，其类型与泛型参数 E 一致
     * @return 如果找到目标则返回其索引位置，否则返回 -1
     */
    int search(E[] elements, E target);
}