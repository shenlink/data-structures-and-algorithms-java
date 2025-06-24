package com.hxqzzxk.sort;

/**
 * 插入排序优化版
 * 将插入排序的交换操作改为赋值操作以提高性能
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class InsertionSortShiftOnly<E extends Comparable<E>> extends Sort<E> {
    /**
     * 对数组进行排序，采用优化插入排序算法。
     * 每轮将当前元素向前移动到合适位置，减少不必要的交换操作。
     * 排序过程：
     * - 从第二个元素开始遍历，每次取出当前位置的元素；
     * - 通过比较与前一个元素的大小，将前面较大的元素向后移动；
     * - 直到找到合适的位置再将当前元素插入；
     * - 这样可以减少交换次数，提升排序效率。
     */
    @Override
    public void sort() {
        // 优化的点：插入排序的优化点，将插入排序的交换操作改为赋值操作
        int current = 1;
        for (int begin = 1; begin < elements.length; begin++) {
            current = begin;
            E v = elements[current];
            // 这里的退出条件是：v比current - 1位置的元素大，所以要在循环结束后在
            // current处赋值v
            while (current > 0 && compare(v, elements[current - 1]) < 0) {
                elements[current] = elements[current - 1];
                current--;
            }
            elements[current] = v;
        }
    }
}
