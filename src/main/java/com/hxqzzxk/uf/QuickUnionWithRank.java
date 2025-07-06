package com.hxqzzxk.uf;

/**
 * 并查集实现 - 基于树高度（rank）优化的快速合并
 */
public class QuickUnionWithRank extends QuickUnion {
    /**
     * 存储每个集合的树高，索引表示根节点，值表示该集合的树高
     */
    private int[] ranks;

    /**
     * 构造函数
     *
     * @param capacity 容量
     */
    public QuickUnionWithRank(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            ranks[i] = 1;
        }
    }

    /**
     * 合并两个集合，合并时考虑两棵树的高度（rank）
     * 高度较小的树合并到高度较大的树上，以避免树的高度增长过快
     *
     * @param v1 第一个元素
     * @param v2 第二个元素
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2)
            return;

        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            // 两个集合的树高一样，随便让一个集合合并到另一个集合，并将合并后的树高度加1
            parents[p1] = p2;
            ranks[p2] += 1;
        }
    }
}
