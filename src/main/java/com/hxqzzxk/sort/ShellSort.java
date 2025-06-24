package com.hxqzzxk.sort;

import java.util.ArrayList;
import java.util.List;

// 希尔排序
public class ShellSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    public void sort() {
        List<Integer> stepSequence = getStepSequence();
        for (int step : stepSequence) {
            sort(step);
        }
    }

    // 使用希尔排序算法对数组进行排序
    // 希尔排序是插入排序的一种高效率的改进版本，它通过比较相距一定步长的元素来工作，
    // 经过多次步长比较，最终步长降为1，进行常规的插入排序，从而使得数组有序
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


    // 步长序列
    private List<Integer> getStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = elements.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }
}
