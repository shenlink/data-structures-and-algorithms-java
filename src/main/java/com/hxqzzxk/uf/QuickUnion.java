package com.hxqzzxk.uf;

/**
 * 并查集实现 - 快速合并（Quick Union）
 */
public class QuickUnion extends UnionFind {
    /**
     * 构造函数
     *
     * @param capacity 集合容量
     */
    public QuickUnion(int capacity) {
        super(capacity);
    }

    /**
     * 查找元素 v 所在集合的根节点（路径压缩优化可在子类中实现）
     *
     * @param v 待查找的元素索引
     * @return 元素 v 所在集合的根节点
     */
    @Override
    public int find(int v) {
        validate(v);
        // 沿着父节点不断向上查找，直到找到根节点
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    /**
     * 将两个元素所在的集合合并
     * 合并策略为：将第一个集合的根节点直接指向第二个集合的根节点
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

        parents[p1] = p2;
    }
}
