package com.hxqzzxk.uf;

/**
 * 并查集实现 - 路径分裂优化（Path Splitting），提升查找效率
 */
public class QuickUnionWithPathSplit extends QuickUnionWithRank {
    /**
     * 构造函数
     *
     * @param capacity 集合容量
     */
    public QuickUnionWithPathSplit(int capacity) {
        super(capacity);
    }

    /**
     * 查找元素 v 所在集合的根节点，并在查找过程中进行路径分裂
     * 路径分裂使每个节点指向其祖父节点，从而减少树的高度
     *
     * @param v 待查找的元素索引
     * @return 元素 v 所在集合的根节点
     */
    public int find(int v) {
        while (v != parents[v]) {
            int parent = parents[v];
            parents[v] = parents[parent];
            v = parent;
        }
        return v;
    }
}
