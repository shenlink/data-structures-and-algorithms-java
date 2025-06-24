package com.hxqzzxk.sort;

/**
 * 快速排序优化版实现类
 * 基于分治策略进行排序，采用双路快速排序算法，避免极端情况下的性能退化。
 * 时间复杂度平均为 O(n log n)，最差情况下为 O(n^2)，空间复杂度为 O(log n)。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class QuickSortDualPivot<E extends Comparable<E>> extends Sort<E> {
    /**
     * 对数组进行排序，默认从整个数组范围开始排序。
     */
    @Override
    public void sort() {
        sort(0, elements.length - 1);
    }

    /**
     * 对指定范围内的数组进行快速排序。
     * <p>
     * 排序过程分为以下几个步骤：
     * 1. 如果当前范围小于两个元素，无需排序；
     * 2. 使用 partition 方法将数组划分为两个子数组；
     * 3. 对左右两个子数组分别递归排序。
     *
     * @param begin 排序范围的起始索引（包含）
     * @param end   排序范围的结束索引（不包含）
     */
    private void sort(int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = partition(begin, end);
        sort(begin, mid - 1);
        sort(mid + 1, end);
    }

    /**
     * 将指定范围内的数组按照基准元素进行划分。
     * <p>
     * 划分过程分为以下几个步骤：
     * 1. 随机选择一个基准元素，并将其交换到起始位置；
     * 2. 定义两个指针 l 和 r，分别从左右两侧向中间扫描；
     * 3. 找到左侧大于等于基准的元素和右侧小于等于基准的元素并交换；
     * 4. 当两指针相遇时停止扫描，将基准元素与右指针指向的元素交换；
     * 5. 返回右指针的位置作为基准位置。
     *
     * @param begin 划分范围的起始索引（包含）
     * @param end   划分范围的结束索引（不包含）
     * @return 基准元素最终所在的位置索引
     */
    private int partition(int begin, int end) {
        swap(begin, begin + (int) (Math.random() * (end - begin + 1)));
        int l = begin + 1;
        int r = end;
        while (true) {
            // 从左边的l开始，如果元素一直小于begin，则继续往右，
            // 否则跳出循环，注意，l <= r，因为当l > r，则说明
            // 已经遍历完所有的元素了
            while (l <= r && compare(l, begin) < 0) {
                l++;
            }
            // 从右边r开始，如果元素一直大于begin，则继续往左，
            // 否则跳出循环，注意，r >= l，因为当r < l，
            // 则说明已经遍历完所有的元素了
            while (r >= l && compare(r, begin) > 0) {
                r--;
            }
            // l >= r，则说明已经遍历完所有的元素了，可以跳出循环了
            if (l >= r) {
                break;
            }
            // 这里需要交换l和r，因为来到这里，说明l对应的元素是大于begin的，
            // r对应的元素是小于begin的，需要交换位置，才能维持住左边的是小于begin的，
            // 右边的元素是大于begin的
            // 交换完成后，l++，r--，跳过当前的两个元素
            // 注意：一个元素是等于begin的时候，也会触发交换，这样，等于begin的元素
            // 会更加均匀地分布在中心索引两边
            swap(l, r);
            l++;
            r--;
        }
        // 交换begin和r，才能维持住左边的是小于等于begin的，右边的元素是大于等于begin的
        // 注意：不能改成swap(l, r)，因为此时r对应的位置的元素是小于begin的，
        // 交换后，还能保证r左边的小于begin，右边的大于begin，
        // 如果改成swap(begin, l)，则会使得0索引的元素是大于begin的，
        // 不能保证左边是小于begin的，右边是大于begin的
        swap(begin, r);
        return r;
    }
}
