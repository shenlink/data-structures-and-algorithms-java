package com.hxqzzxk.sort;

/**
 * 插入排序实现类
 * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class InsertionSortBasic<E extends Comparable<E>> extends Sort<E> {
    /**
     * 执行插入排序的具体逻辑。
     * <p>
     * 排序过程：
     * - 循环 n - 1 轮，每轮将当前元素插入到前面已经排序的元素中的合适位置；
     * - 从当前元素开始往前找，如果当前元素比前一个元素小，则交换位置；
     * - 交换之后，继续向前查看，直到找到合适的位置或者到达数组起始位置。
     */
    @Override
    public void sort() {
        // 插入排序类似于扑克牌排序
        // 循环n - 1轮，每轮将当前元素插入到前面已经排序的元素中的合适位置
        int current = 1;
        // 循环n - 1轮
        for (int begin = 1; begin < elements.length; begin++) {
            // 从current开始往前找，找到当前元素应该插入的位置
            current = begin;
            // 如果current > 0，且当前元素比前一个元素小，则交换位置，
            // 交换之后，current--，继续查看，找到当前元素应该插入的位置
            while (current > 0 && compare(current, current - 1) < 0) {
                swap(current, current - 1);
                current--;
            }
        }
    }
}
