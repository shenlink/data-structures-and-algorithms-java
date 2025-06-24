package com.hxqzzxk.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 桶排序实现类
 * 将数组中的元素分发到多个桶中，每个桶分别排序后合并回原数组。
 * 适用于数据分布较均匀的场景，时间复杂度接近 O(n)，空间复杂度较高。
 */
public class BucketSort extends Sort<Integer> {

    /**
     * 对数组进行桶排序。
     * 
     * 排序过程分为以下几个步骤：
     * 1. 确定数组非空；
     * 2. 找出最大值用于归一化处理；
     * 3. 创建桶并分配元素；
     * 4. 对每个桶进行排序；
     * 5. 合并所有桶的元素回原数组。
     */
      @Override  public void sort() {
        // 确保数组非空
        if (elements == null || elements.length == 0) return;

        int n = elements.length;

        // 找到最大值以便归一化
        int max = Integer.MIN_VALUE;
        for (Integer element : elements) {
            if (element == null) continue;
            if (element > max) max = element;
        }

        // 创建桶数组，每个桶用 LinkedList 存储
        List<List<Integer>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            buckets.add(new LinkedList<>());
        }

        // 将元素分配到对应桶中
        for (Integer element : elements) {
            if (element == null) continue;
            int bucketIndex = (int) ((double) element / (max + 1) * n); // 归一化处理
            buckets.get(bucketIndex).add(element);
        }

        // 对每个桶分别排序
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // 合并所有桶的元素回原数组
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (Integer value : bucket) {
                elements[index++] = value;
            }
        }
    }
}