package com.hxqzzxk.sort;

/**
 * 计数排序实现类
 * 基于元素值范围统计频率进行排序，适用于小范围整型数据集。
 * 时间复杂度 O(n + k)，其中 k 是数据值范围，空间复杂度 O(k)。
 */
public class CountingSort extends Sort<Integer> {
    /**
     * 对数组进行计数排序。
     * <p>
     * 排序过程分为以下几个步骤：
     * 1. 找出数组中的最小值和最大值以确定值范围；
     * 2. 创建计数数组并统计每个元素出现的次数；
     * 3. 构建前缀和数组以确定每个元素在输出数组中的位置；
     * 4. 从后向前遍历原数组，将元素放置到正确位置以保证稳定性；
     * 5. 将排序结果复制回原数组。
     */
    @Override
    public void sort() {
        // 先计算出数组中的最小值和最大值
        int min = elements[0];
        int max = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] < min) {
                min = elements[i];
            }
            if (elements[i] > max) {
                max = elements[i];
            }
        }
        // 开辟数组
        int[] counts = new int[max - min + 1];
        // 计算出数组中每个元素出现的次数
        for (int i = 0; i < elements.length; i++) {
            counts[elements[i] - min]++;
        }
        // 计算counts数组，里面的每个元素表述的是前面的元素出现的次数加上自己出现的次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // 开辟新数组，存放排序结果
        int[] newArray = new int[elements.length];
        // 从后面开始遍历原数组
        for (int i = elements.length - 1; i >= 0; i--) {
            // --counts[elements[i] - min]，先--，得出当前元素在排序后的位置，
            // 因为每个元素存储的是前面的元素出现的次数加上自己出现的次数，所以这样操作
            // 就可以得到元素的真实排序位置
            newArray[--counts[elements[i] - min]] = elements[i];
        }
        // 放回原数组
        for (int i = 0; i < newArray.length; i++) {
            elements[i] = newArray[i];
        }
    }
}
