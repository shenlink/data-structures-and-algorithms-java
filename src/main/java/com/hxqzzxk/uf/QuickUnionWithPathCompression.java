package com.hxqzzxk.uf;

/**
 * 并查集实现 - 路径压缩优化（递归实现），提升查找效率
 */
public class QuickUnionWithPathCompression extends QuickUnionWithRank {
    /**
     * 构造函数
     *
     * @param capacity 集合容量
     */
    public QuickUnionWithPathCompression(int capacity) {
        super(capacity);
    }

    /**
     * 查找元素 v 所在集合的根节点，并在查找过程中进行路径压缩
     * 路径压缩使得查找路径上的所有节点都直接指向根节点，降低树的高度
     *
     * @param v 待查找的元素索引
     * @return 元素 v 所在集合的根节点
     */
    @Override
    public int find(int v) {
        validate(v);
        while (v != parents[v]) {
            v = find(parents[v]);
        }

        return parents[v];
    }
}
