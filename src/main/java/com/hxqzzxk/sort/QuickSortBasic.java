package com.hxqzzxk.sort;

/**
 * 快速排序实现类
 * 基于分治策略进行排序，通过选定基准元素将数组划分为两个子数组，分别进行递归排序。
 * 时间复杂度平均为 O(n log n)，最差情况下为 O(n^2)，空间复杂度为 O(log n)。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class QuickSortBasic<E extends Comparable<E>> extends Sort<E> {

    /**
     * 对数组进行排序，默认从整个数组范围开始排序。
     */
    @Override
    public void sort() {
        sort(0, elements.length);
    }

    /**
     * 对指定范围内的数组进行快速排序。
     * <p>
     * 排序过程分为以下几个步骤：
     * 1. 如果当前范围小于两个元素，无需排序；
     * 2. 随机选择一个基准元素，并将其交换到起始位置；
     * 3. 使用 partition 方法将数组划分为两个子数组；
     * 4. 对左右两个子数组分别递归排序。
     *
     * @param begin 排序范围的起始索引（包含）
     * @param end   排序范围的结束索引（不包含）
     */
    private void sort(int begin, int end) {
        // 只有一个元素，不用排序
        if (end - begin < 2) {
            return;
        }
        int mid = partition(begin, end);
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 将指定范围内的数组按照基准元素进行划分。
     * <p>
     * 划分过程分为以下几个步骤：
     * 1. 随机化选择基准元素并将其交换到起始位置，防止退化成 O(n^2)；
     * 2. 初始化指针 j 指向起始位置；
     * 3. 遍历数组，如果当前元素小于基准元素，则将其交换到 j 的下一个位置；
     * 4. 最终将基准元素交换到正确位置，并返回其索引。
     *
     * @param begin 划分范围的起始索引（包含）
     * @param end   划分范围的结束索引（不包含）
     * @return 基准元素最终所在的位置索引
     */
    private int partition(int begin, int end) {
        // 随机化begin的元素，防止快速排序退化成O(n^2)
        swap(begin, begin + (int) (Math.random() * (end - begin)));
        int j = begin;

        // arr[begin+1...j] < v ; arr[j+1...i] >= v
        for (int i = begin + 1; i < end; i++) {
            // 如果当前元素小于基准元素，则交换位置
            // 如果是大于等于的话，那就不用管，因为arr[j+1, i]都大于等于begin
            if (compare(i, begin) < 0) {
                j++;
                swap(i, j);
            }
        }
        // 此时的j对应的元素是小于基准元素的
        swap(begin, j);
        return j;
    }
}
