package com.hxqzzxk.sort;

/**
 * 3路快速排序实现类
 * 基于分治策略进行排序，采用三向切分方式处理重复元素，适用于包含大量重复元素的数组。
 * 时间复杂度平均为 O(n log n)，最差情况下为 O(n^2)，空间复杂度为 O(log n)。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class QuickSort3Way<E extends Comparable<E>> extends Sort<E> {
    /**
     * 对数组进行排序，默认从整个数组范围开始排序。
     */
    @Override
    public void sort() {
        sort(0, elements.length);
    }

    /**
     * 对指定范围内的数组进行3路快速排序。
     * <p>
     * 排序过程分为以下几个步骤：
     * 1. 如果起始索引大于等于结束索引，则无需排序，直接返回；
     * 2. 随机选择一个基准元素，并将其交换到起始位置；
     * 3. 定义三个指针 lt、i 和 gt 分别指向小于、等于和大于基准值的区域；
     * 4. 遍历数组并根据当前元素与基准值的比较结果调整元素位置；
     * 5. 将基准值放到正确的位置上，并对左右两个子数组分别递归排序。
     *
     * @param begin 排序范围的起始索引（包含）
     * @param end   排序范围的结束索引（不包含）
     */
    private void sort(int begin, int end) {
        // 如果起始索引大于等于结束索引，则无需排序，直接返回
        if (begin >= end) return;

        // 在begin和end之间随机选择一个索引，并将其值与begin位置的值交换
        // 这是为了提高算法的性能，避免最坏情况的发生
        swap(begin, begin + (int) (Math.random() * (end - begin)));

        // lt是小于分区元素的最后一个索引
        int lt = begin;
        // i是当前考虑的元素的索引
        int i = begin + 1;
        // gt是大于分区元素的第一个索引
        int gt = end;
        // 遍历数组，根据元素与分区元素的比较结果进行交换
        while (i < gt) {
            // 如果当前元素小于分区元素，则将其与lt位置的元素交换，并向前移动lt和i
            if (compare(i, begin) < 0) {
                lt++;
                swap(i, lt);
                i++;
            }
            // 如果当前元素大于分区元素，则将其与gt位置的元素交换，并向后移动gt
            else if (compare(i, begin) > 0) {
                gt--;
                swap(i, gt);
            }
            // 如果当前元素等于分区元素，则向前移动i
            else {
                i++;
            }
        }

        // 将分区元素与lt位置的元素交换，以确保分区元素位于正确的位置
        swap(begin, lt);
        // 递归地对分区元素左侧和右侧的子数组进行排序
        sort(begin, lt);
        sort(gt, end);
    }
}
