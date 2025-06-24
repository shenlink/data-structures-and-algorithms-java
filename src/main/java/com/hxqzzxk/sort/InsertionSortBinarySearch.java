package com.hxqzzxk.sort;

/**
 * 插入排序优化版2
 * 使用二分查找确定插入位置以减少比较次数，同时采用赋值操作代替交换操作以提高性能
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class InsertionSortBinarySearch<E extends Comparable<E>> extends Sort<E> {
    /**
     * 对数组进行排序，采用双重优化插入排序算法。
     * 每轮将当前元素向前移动到合适位置，通过二分查找确定插入位置，
     * 减少不必要的比较操作，并采用赋值方式替代交换操作提升效率。
     */
    @Override
    public void sort() {
        for (int begin = 1; begin < elements.length; begin++) {
            insert(begin, search(begin));
        }
    }

    /**
     * 将指定索引处的元素插入到目标索引位置。
     * 类似于动态数组的插入操作，索引dest之后的元素都向后移动一位，
     * 在dest位置插入element。
     *
     * @param source 要插入的元素原始索引
     * @param dest   目标插入位置
     */
    private void insert(int source, int dest) {
        E element = elements[source];
        // 类似于动态数组的插入操作，索引dest之后的元素都向后移动一位，
        // 在dest位置插入element
        for (int i = source; i > dest; i--) {
            elements[i] = elements[i - 1];
        }
        elements[dest] = element;
    }

    /**
     * 二分搜索找到索引index所在元素的待插入位置。
     * 返回应该插入的位置索引。
     *
     * @param index 当前要插入的元素索引
     * @return 待插入位置索引
     */
    private int search(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (compare(index, mid) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
