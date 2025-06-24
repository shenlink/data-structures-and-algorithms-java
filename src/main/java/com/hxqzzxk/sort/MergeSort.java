package com.hxqzzxk.sort;

/**
 * 归并排序实现类
 * 基于分治策略进行排序，将数组分为两个子数组分别排序后合并，
 * 时间复杂度为 O(n log n)，空间复杂度为 O(n)。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class MergeSort<E extends Comparable<E>> extends Sort<E> {
    /**
     * 左边的数组，可以重复利用以减少内存分配
     */
    private E[] leftArray;

    /**
     * 执行归并排序，默认从整个数组范围开始排序。
     */
    @SuppressWarnings("unchecked")
    @Override
    public void sort() {
        // 左边的数组
        leftArray = (E[]) new Comparable[elements.length >> 1];

        sort(0, elements.length);
    }

    /**
     * 对指定范围内的数组进行归并排序。
     * <p>
     * 排序过程分为以下几个步骤：
     * 1. 如果当前范围小于两个元素，无需排序；
     * 2. 将数组分为左右两部分，分别递归排序；
     * 3. 将排序好的两部分进行归并操作。
     *
     * @param begin 排序范围的起始索引（包含）
     * @param end   排序范围的结束索引（不包含）
     */
    private void sort(int begin, int end) {
        // 只有一个元素，不需要排序
        if (end - begin < 2) {
            return;
        }

        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 归并操作，将两个有序子数组合并成一个有序数组。
     * <p>
     * 合并过程分为以下几个步骤：
     * 1. 复制左边的数组到临时数组中；
     * 2. 依次比较左右两个子数组的元素，将较小的元素放入原数组；
     * 3. 当其中一个子数组处理完后，将另一个子数组剩余元素直接复制过去。
     *
     * @param begin 排序范围的起始索引（包含）
     * @param mid   数组中间位置索引，用于划分两个子数组
     * @param end   排序范围的结束索引（不包含）
     */
    private void merge(int begin, int mid, int end) {
        int li = 0;
        int le = mid - begin;
        int ri = mid;
        int re = end;
        int ai = begin;
        // 备份左边的数组
        for (int i = li; i < le; i++) {
            leftArray[i] = elements[begin + i];
        }
        // li小于le，因为这是一个左闭右开的区间
        // 注意：这里不能是ri < re，这里是ri < re，下面的if中是ri < re，
        // 这是配套的，不能修改顺序
        while (li < le) {
            // ri >= re时，剩下的都是左边的数组的值，
            // 也就是直接把leftArray剩下的值复制到elements剩下的位置中
            if (ri < re && compare(elements[ri], leftArray[li]) < 0) {
                elements[ai] = elements[ri];
                ai++;
                ri++;
            } else {
                elements[ai] = leftArray[li];
                ai++;
                li++;
            }
        }
    }
}
