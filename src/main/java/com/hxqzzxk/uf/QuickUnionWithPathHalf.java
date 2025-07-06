package com.hxqzzxk.uf;

/**
 * 并查集实现 - 路径减半优化（Path Halving），提升查找效率
 */
public class QuickUnionWithPathHalf extends QuickUnionWithRank {
    /**
     * 构造函数
     *
     * @param capacity 集合容量
     */
    public QuickUnionWithPathHalf(int capacity) {
        super(capacity);
    }

    /**
     * 查找元素 v 所在集合的根节点，并在查找过程中进行路径减半
     * 路径减半使每隔一个节点就指向祖父节点，从而减少树的高度
     *
     * @param v 待查找的元素索引
     * @return 元素 v 所在集合的根节点
     */
    public int find(int v) {
        while (v != parents[v]) {
            // 当前节点指向祖父节点
            parents[v] = parents[parents[v]];
            // 注意：这里跳过v的父节点，v的父节点还是会指向v的祖父节点
            v = parents[v];
        }
        return v;
    }
}
