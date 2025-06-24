package com.hxqzzxk.sort;

/**
 * 冒泡排序优化版
 * 通过记录每轮最后一次交换的位置，减少无意义的比较范围
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BubbleSortLastSwap<E extends Comparable<E>> extends Sort<E> {

    /**
     * 对数组进行排序，采用冒泡排序优化算法。
     * 每一轮遍历中记录最后一个交换的位置，作为下一轮比较的边界，
     * 减少不必要的比较次数，提高效率。
     */
    @Override
    public void sort() {
        // 有序区的边界，每次循环都将无序区间的边界向左移动一位
        int sortedIndex = 1;
        // 执行n - 1轮循环
        for (int end = elements.length - 1; end > 0; end--) {
            // 这里设置为1，在这轮循环中，如果没有发生一次交换，
            // 那end = sortedIndex，则下一轮循环时，end == 0，结束循环
            sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (compare(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            // sortedIndex是最后一次有序的位置，
            // end = sortedIndex，下一轮循环时，
            // end = sortedIndex - 1
            end = sortedIndex;
        }
    }
}