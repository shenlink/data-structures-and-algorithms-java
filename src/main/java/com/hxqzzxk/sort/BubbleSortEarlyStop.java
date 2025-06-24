package com.hxqzzxk.sort;

/**
 * 冒泡排序优化版
 * 通过提前终止有序序列减少不必要的比较次数
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BubbleSortEarlyStop<E extends Comparable<E>> extends Sort<E> {
    /**
     * 对数组进行排序，采用优化冒泡排序算法。
     * 每一轮遍历中，如果未发生交换，说明数组已有序，可提前结束排序。
     * <p>
     * 排序逻辑：
     * - 控制循环轮数（n - 1 轮）
     * - 每一轮从索引 1 开始比较相邻元素，若顺序错误则交换位置
     * - 使用 isSorted 标记判断是否已经有序，提前终止排序过程
     */
    @Override
    public void sort() {
        // 是否已经有序
        boolean isSorted = true;
        // 执行n - 1轮
        for (int end = elements.length - 1; end > 0; end--) {
            // 每一轮执行都默认有序，发生交换后，有序变为false
            isSorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (compare(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    isSorted = false;
                }
            }
            // 如果已经有序，则提前结束
            if (isSorted) {
                break;
            }
        }
    }
}
