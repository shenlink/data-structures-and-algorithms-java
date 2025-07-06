package com.hxqzzxk.uf;

/**
 * 并查集实现 - 快速查找（Quick Find），牺牲合并性能换取查找性能
 */
public class QuickFind extends UnionFind {
    /**
     * 构造函数
     *
     * @param capacity 集合容量
     */
    public QuickFind(int capacity) {
        super(capacity);
    }

    /**
     * 查找元素 v 所在集合的根节点
     * 在 QuickFind 实现中，每个元素直接存储其根节点
     *
     * @param v 待查找的元素索引
     * @return 元素 v 所在集合的根节点
     */
    @Override
    public int find(int v) {
        validate(v);
        return parents[v];
    }

    /**
     * 合并两个集合，即将所有与 v1 同属一个集合的元素的根节点设为 v2 的根节点
     *
     * @param v1 第一个元素
     * @param v2 第二个元素
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }

        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }
}
