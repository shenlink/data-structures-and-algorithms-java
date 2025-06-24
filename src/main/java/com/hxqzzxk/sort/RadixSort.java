package com.hxqzzxk.sort;

/**
 * 基数排序实现类
 * 底层使用的是基数排序，适用于整型数据集
 * 时间复杂度为 O(n * k)，其中 k 是最大数字的位数，空间复杂度为 O(n)
 */
public class RadixSort extends Sort<Integer> {
    /**
     * 对数组进行基数排序。
     * 
     * 排序过程分为以下几个步骤：
     * 1. 找出数组中的最大值以确定排序轮数；
     * 2. 每一轮对个位、十位、百位等依次进行排序；
     * 3. 使用计数排序作为子过程对每一位进行排序处理。
     */
    @Override
    public void sort() {
        // 求出数组中的最大值
        int max = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }

        // divider：进制
        // 先对个位数进行排序，然后是十位数，之后是百位数，千位数，以此类推
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSort(divider);
        }
    }

    /**
     * 对指定的位数进行计数排序。
     * 
     * @param divider 当前排序的位数因子（如 1, 10, 100 等）
     */
    private void countingSort(int divider) {
        // 获取一个数字的对应位数的数值：value / divider % 10
        int min = elements[0] / divider % 10;
        int max = elements[0] / divider % 10;
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] / divider % 10 < min) {
                min = elements[i] / divider % 10;
            }
            if (elements[i] / divider % 10 > max) {
                max = elements[i] / divider % 10;
            }
        }
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < elements.length; i++) {
            counts[(elements[i] - min) / divider % 10]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        int[] newArray = new int[elements.length];
        for (int i = elements.length - 1; i >= 0; i--) {
            newArray[--counts[(elements[i] - min) / divider % 10]] =
                    elements[i];
        }
        for (int i = 0; i < newArray.length; i++) {
            elements[i] = newArray[i];
        }
    }
}
