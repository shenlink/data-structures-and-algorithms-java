package com.hxqzzxk.sort;

/**
 * 堆排序实现类
 * 利用最大堆的特性进行排序，通过构建堆并逐步取出堆顶元素完成排序。
 * 时间复杂度 O(n log n)，空间复杂度 O(1)，属于原地排序算法。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    /**
     * 堆大小，用于记录当前堆中的元素个数。
     */
    private int heapSize;

    /**
     * 对数组进行堆排序的具体逻辑。
     * 
     * 排序过程分为以下几个步骤：
     * 1. 构建最大堆：从最后一个非叶子节点开始，依次向上执行下滤操作；
     * 2. 交换堆顶和堆底元素，并减少堆大小；
     * 3. 对新的堆顶执行下滤操作以恢复堆性质；
     * 4. 重复上述步骤直到堆中只剩一个元素。
     */
    @Override
    public void sort() {
        heapSize = elements.length;
        // 原地建堆
        // 此时索引0处为最大值
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        // 循环，从堆中取出最大值，放到数组末尾，
        // 从n到1，heapSize是2的时候，此时堆中只有2个元素，
        // 交换之后，堆顶就是最小的元素了，此时结束循环
        while (heapSize > 1) {
            // 交换堆顶和堆底
            swap(0, --heapSize);
            // 下滤
            siftDown(0);
        }
    }

    /**
     * 对指定索引位置的节点执行下滤操作，以维护最大堆的性质。
     * 
     * @param index 要下滤的节点索引
     */
    private void siftDown(int index) {
        E element = elements[index];
        int half = heapSize >> 1;

        while (index < half) {
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightIndex = childIndex + 1;
            if (rightIndex < heapSize) {
                E right = elements[rightIndex];
                if (compare(right, child) > 0) {
                    child = right;
                    childIndex = rightIndex;
                }
            }
            if (compare(element, child) >= 0) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }
}