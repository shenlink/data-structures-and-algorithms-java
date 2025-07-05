package com.hxqzzxk.set;

/**
 * 集合接口，定义了集合的基本操作方法。
 * 该接口为不同的集合实现提供了统一的 API 规范。
 */
public interface Set<E> {
    /**
     * 清空集合中的所有元素。
     */
    void clear();

    /**
     * 返回当前集合中元素的数量。
     *
     * @return 元素个数
     */
    int size();

    /**
     * 检查集合是否为空。
     *
     * @return 如果集合没有元素则返回 true
     */
    boolean isEmpty();

    /**
     * 判断集合中是否包含指定元素。
     *
     * @param element 要查找的元素
     * @return 如果找到元素则返回 true
     */
    boolean contains(E element);

    /**
     * 向集合中添加一个元素。
     *
     * @param element 要添加的元素
     */
    void add(E element);

    /**
     * 从集合中删除指定的元素。
     *
     * @param element 要删除的元素
     */
    void remove(E element);

    /**
     * 遍历集合中的所有元素。
     *
     * @param visitor 访问器，用于处理每个元素
     */
    void traversal(Visitor<E> visitor);

    /**
     * 抽象访问者类，用于遍历集合元素
     * 
     * @param <E> 集合中存储的元素类型
     */
    public static abstract class Visitor<E> {
        /**
         * 停止遍历标志
         * 当该属性为 true 时，遍历操作应当停止
         */
        boolean stop;

        /**
         * 访问指定元素
         * 
         * @param element 要访问的元素
         * @return 返回 true 停止遍历，返回 false 则继续遍历
         */
        abstract boolean visit(E element);
    }
}
