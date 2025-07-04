package com.hxqzzxk.sort;

/**
 * 选择排序实现类
 * 每轮将最大的元素放在最后
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class SelectionSort<E extends Comparable<E>> extends Sort<E> {
    /**
     * 执行选择排序的具体逻辑。
     * <p>
     * 排序过程分为以下几个步骤：
     * 1. 控制每轮比较的结束位置，初始为数组最后一个索引，之后每次减少一个索引；
     * 2. 每轮找出当前未排序部分的最大元素并将其放置在正确位置；
     * 3. 使用 maxIndex 变量记录最大元素的位置，通过交换操作完成排序。
     *
     * @param begin 起始索引，用于确定每一轮比较的起始位置
     * @param end   结束索引，用于确定每一轮比较的结束位置
     */
    @Override
    public void sort() {
        // 执行n - 1轮，每轮将最大的元素放在最后
        for (int end = elements.length - 1; end > 0; end--) {
            int maxIndex = 0;
            // 确定非排序区间里的最大元素的索引
            for (int begin = 0; begin <= end; begin++) {
                if (compare(maxIndex, begin) < 0) {
                    maxIndex = begin;
                }
            }
            // 与最后一个非排序区间的元素交换
            swap(maxIndex, end);
        }
    }
}
