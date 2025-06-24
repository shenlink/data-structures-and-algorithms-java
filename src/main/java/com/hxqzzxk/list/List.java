package com.hxqzzxk.list;

/**
 * 定义线性表相关操作的接口，包含基本的数据结构操作方法。
 * 该接口为各种线性表实现提供了统一的 API 规范。
 */
public interface List<E> {
    /**
     * 清空所有元素。
     */
    void clear();

    /**
     * 返回当前元素的数量。
     *
     * @return 元素个数
     */
    int size();

    /**
     * 检查线性表是否为空。
     *
     * @return 如果线性表没有元素则返回 true
     */
    boolean isEmpty();

    /**
     * 判断线性表中是否包含指定元素。
     *
     * @param element 要查找的元素
     * @return 如果找到元素则返回 true
     */
    boolean contains(E element);

    /**
     * 将元素添加到线性表尾部。
     *
     * @param element 要添加的元素
     */
    void add(E element);

    /**
     * 获取指定位置的元素。
     *
     * @param index 要获取的元素位置，必须在 [0, size()) 范围内
     * @return 位于指定索引处的元素
     */
    E get(int index);

    /**
     * 替换指定位置的元素。
     *
     * @param index   要替换的位置，必须在 [0, size()) 范围内
     * @param element 新元素
     * @return 被替换的旧元素
     */
    E set(int index, E element);

    /**
     * 在指定位置插入一个元素。
     *
     * @param index   插入位置，必须在 [0, size()] 范围内
     * @param element 要插入的元素
     */
    void add(int index, E element);

    /**
     * 删除指定位置的元素。
     *
     * @param index 要删除的位置，必须在 [0, size()) 范围内
     * @return 被删除的元素
     */
    E remove(int index);

    /**
     * 查找指定元素第一次出现的位置。
     *
     * @param element 要查找的元素
     * @return 元素首次出现的索引位置，如果未找到则返回 -1
     */
    int indexOf(E element);
}
