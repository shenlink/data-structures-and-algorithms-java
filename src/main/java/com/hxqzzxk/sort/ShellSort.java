package com.hxqzzxk.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 希尔排序实现类
 * <p>
 * 希尔排序是插入排序的一种高效率的改进版本，它通过比较相距一定步长的元素来工作，
 * 经过多次步长比较，最终步长降为1，进行常规的插入排序，从而使得数组有序。
 * 该算法在处理大规模乱序数据时，相较于简单的插入排序有显著的性能提升。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public class ShellSort<E extends Comparable<E>> extends Sort<E> {

    /**
     * 对数组进行希尔排序
     * <p>
     * 该方法通过生成一个步长序列（getStepSequence），然后依次使用每个步长对数组进行分组排序。
     * 每次排序根据当前步长将数组划分为多个子序列，并对每个子序列执行插入排序。
     * 随着步长逐渐减小，最后一步会使用步长为1的插入排序完成整体排序。
     */
    @Override
    public void sort() {
        List<Integer> stepSequence = getStepSequence();
        for (int step : stepSequence) {
            sort(step);
        }
    }

    /**
     * 使用希尔排序算法对数组进行排序
     * <p>
     * 希尔排序的核心思想是：将原本大量移动插入排序操作分解为多个小规模插入排序，
     * 每次排序间隔一定的步长，逐步减小步长，直到步长为1时完成最后的插入排序。
     *
     * @param step 当前排序的步长值
     */
    public void sort(int step) {
        // 从初始步长开始，逐步减小步长，对数组进行排序
        int current = 1;
        // 外层循环控制步长的大小，从初始步长step开始，逐渐减小到1
        for (int col = 0; col < step; col++) {
            // 内层循环根据当前步长step，遍历数组，进行插入排序
            for (int begin = col + step; begin < elements.length; begin += step) {
                current = begin;
                E v = elements[begin];
                // 将当前元素v与前面已排序的元素进行比较，找到合适的位置插入
                while (current > col && compare(v, elements[current - step]) < 0) {
                    // 如果当前元素v小于比较的元素，则将比较的元素后移一位
                    elements[current] = elements[current - step];
                    current -= step;
                }
                // 将当前元素v插入到正确的位置
                elements[current] = v;
            }
        }
    }

    /**
     * 获取步长序列
     * <p>
     * 步长序列的选择会影响希尔排序的效率。本方法使用的是最基础的二分法生成步长序列：
     * 即每次将数组长度除以2，直到步长为1。
     *
     * @return 包含步长值的列表
     */
    private List<Integer> getStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = elements.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }
}