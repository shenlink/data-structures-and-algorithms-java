package com.hxqzzxk.uf;

/**
 * 并查集抽象类，提供并查集的基本操作定义
 */
public abstract class UnionFind {
    /**
     * 存储每个节点的父节点索引数组，索引表示节点，值表示该节点的父节点索引
     */
    protected int[] parents;

    /**
     * 构造函数，初始化并查集
     *
     * @param capacity 并查集的容量
     * @throws IllegalArgumentException 如果capacity小于0则抛出异常
     */
    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        parents = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查找指定元素所在集合的根节点（代表元）
     *
     * @param v 要查找的元素
     * @return 根节点的索引
     */
    public abstract int find(int v);

    /**
     * 合并两个元素所属的集合
     *
     * @param v1 第一个元素
     * @param v2 第二个元素
     */
    public abstract void union(int v1, int v2);

    /**
     * 判断两个元素是否属于同一个集合
     *
     * @param v1 第一个元素
     * @param v2 第二个元素
     * @return 如果两个元素在同一个集合中返回true，否则返回false
     */
    public boolean isConnected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /**
     * 验证给定的索引是否在有效范围内
     *
     * @param v 要验证的索引
     * @throws IllegalArgumentException 如果v不在有效范围内则抛出异常
     */
    protected void validate(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v must between 0 and " + (parents.length - 1));
        }
    }
}
