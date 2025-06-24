package com.hxqzzxk.sort;

/**
 * 双路快速排序的另外一种思路
 * 基于分治策略进行排序，采用双路快速排序算法，避免极端情况下的性能退化。
 * 时间复杂度平均为 O(n log n)，最差情况下为 O(n^2)，空间复杂度为 O(log n)。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class QuickSortDualScan<E extends Comparable<E>> extends Sort<E> {
    /**
     * 对数组进行排序，默认从整个数组范围开始排序。
     */
    @Override
    public void sort() {
        sort(0, elements.length);
    }

    /**
     * 对指定范围内的数组进行快速排序。
     *
     * 排序过程分为以下几个步骤：
     * 1. 如果当前范围小于两个元素，无需排序；
     * 2. 使用 partition 方法将数组划分为两个子数组；
     * 3. 对左右两个子数组分别递归排序。
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
     *
     * 划分过程分为以下几个步骤：
     * 1. 随机选择一个基准元素，并将其交换到起始位置；
     * 2. 定义两个指针 i 和 j，分别从左右两侧向中间扫描；
     * 3. 找到左侧大于等于基准的元素和右侧小于等于基准的元素并交换；
     * 4. 当两指针相遇时停止扫描，将基准元素与右指针指向的元素交换；
     * 5. 返回右指针的位置作为基准位置。
     *
     * @param begin 划分范围的起始索引（包含）
     * @param end   划分范围的结束索引（不包含）
     * @return 基准元素最终所在的位置索引
     */
    private int partition(int begin, int end) {
        swap(begin, begin + (int) (Math.random() * (end - begin)));
        E pivot = elements[begin];
        end--;
        while (begin < end) {
            while (begin < end) {
                // end，也就是右边的元素大于pivot，则end--，继续比较
                if (compare(pivot, elements[end]) < 0) {
                    end--;
                } else {// 当前元素小于pivot，与begin交换位置，并且begin++，然后跳出循环
                    swap(begin, end);
                    // 这里begin++，是因为begin索引的值已经是小于pivot的了
                    begin++;
                    break;
                }
            }
            while (begin < end) {
                // begin，也就是左边的元素小于基准元素，则begin++，继续比较
                if (compare(pivot, elements[begin]) > 0) {
                    begin++;
                } else {// 当前元素大于pivot，与end交换位置，并且end--，然后跳出循环
                    swap(begin, end);
                    // 这里end--，是因为end索引的值已经是大于pivot的了
                    end--;
                    break;
                }
            }
        }
        // 此时的begin对应的元素是小于等于pivot的，所以，pivot应该在begin这个位置上
        elements[begin] = pivot;
        return begin;
    }
}
