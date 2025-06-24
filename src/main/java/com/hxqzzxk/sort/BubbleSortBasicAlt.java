package com.hxqzzxk.sort;

/**
 * 冒泡排序基础版的另一种实现
 * 该实现同样完成基本冒泡排序功能，但采用了不同的内部逻辑。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class BubbleSortBasicAlt<E extends Comparable<E>> extends Sort<E> {

    /**
     * 使用另一种方式实现基础冒泡排序。
     * 每轮遍历将最大的元素“冒泡”到数组末尾。
     */
    @Override
    public void sort() {
        for (int i = 0; i < elements.length - 1; i++) {
            // 每次遍历减少一个元素，因为最后一个元素已经到位
            for (int j = 0; j < elements.length - 1 - i; j++) {
                if (compare(j, j + 1) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }
}