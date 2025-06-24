package com.hxqzzxk.sort;

/**
 * 排序抽象类，定义排序的基本操作和通用方法。
 * 子类需实现具体的排序算法。
 *
 * @param <E> 元素类型，必须实现 Comparable 接口
 */
public abstract class Sort<E extends Comparable<E>> {
    /**
     * 待排序的元素数组
     */
    protected E[] elements;

    /**
     * 对指定的元素数组进行排序。
     * 如果数组为 null 或长度小于 2，则无需排序直接返回。
     *
     * @param elements 待排序的元素数组
     */
    public void sort(E[] elements) {
        if (elements == null || elements.length < 2) {
            return;
        }
        this.elements = elements;
        sort();
    }

    /**
     * 抽象排序方法，子类必须实现具体的排序逻辑。
     */
    public abstract void sort();

    /**
     * 交换两个指定索引位置的元素。
     *
     * @param i 第一个元素的索引
     * @param j 第二个元素的索引
     */
    protected void swap(int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * 比较两个索引位置上的元素。
     *
     * @param i1 第一个元素的索引
     * @param i2 第二个元素的索引
     * @return 比较结果：负值表示 i1 处元素小于 i2 处元素，0 表示相等，正值表示大于
     */
    protected int compare(int i1, int i2) {
        return elements[i1].compareTo(elements[i2]);
    }

    /**
     * 比较两个元素的大小。
     *
     * @param e1 第一个元素
     * @param e2 第二个元素
     * @return 比较结果：负值表示 e1 小于 e2，0 表示相等，正值表示大于
     */
    protected int compare(E e1, E e2) {
        return e1.compareTo(e2);
    }
}
